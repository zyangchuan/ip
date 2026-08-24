package mono.storage;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mono.task.Deadline;
import mono.task.Event;
import mono.task.Task;
import mono.task.ToDo;
import mono.testutil.StorageTestSupport;

/**
 * Tests saving and loading task records from isolated test storage.
 */
public class StorageTest {
    private static final Path STORAGE_FILE = Path.of("data", "tasks.txt");

    @BeforeEach
    public void resetStorage() throws IOException {
        StorageTestSupport.resetStorage();
    }

    @Test
    public void saveTasks_variedTasks_readStorageReturnsEquivalentTasks() {
        ToDo todo = new ToDo("read a book");
        Deadline deadline = new Deadline("submit report", "2026-09-01");
        Event event = new Event("project retreat", "2026-09-02", "2026-09-03");
        deadline.markDone();
        Storage storage = new Storage();

        storage.saveTasks(new Task[]{todo, deadline, event});

        assertArrayEquals(
                new String[]{
                    "T | 0 | read a book",
                    "D | 1 | submit report | 2026-09-01",
                    "E | 0 | project retreat | 2026-09-02 | 2026-09-03"
                },
                toFileStrings(storage.readStorage()));
    }

    @Test
    public void saveTasks_existingTasks_replacesStoredTasks() {
        Storage storage = new Storage();
        storage.saveTasks(new Task[]{new ToDo("old task")});

        storage.saveTasks(new Task[]{new ToDo("replacement task")});

        assertArrayEquals(
                new String[]{"T | 0 | replacement task"},
                toFileStrings(storage.readStorage()));
    }

    @Test
    public void readStorage_blankAndMalformedRecords_returnsOnlyValidTasks() throws IOException {
        new Storage();
        Files.writeString(
                STORAGE_FILE,
                String.join(
                        System.lineSeparator(),
                        "",
                        "T | 0 | valid task",
                        "T | 2 | invalid status",
                        "D | 0 | missing date",
                        "E | 1 | event | 2026-09-02 | 2026-09-03",
                        "X | 0 | unknown task type",
                        "malformed"));

        Task[] tasks = new Storage().readStorage();

        assertArrayEquals(
                new String[]{
                    "T | 0 | valid task",
                    "E | 1 | event | 2026-09-02 | 2026-09-03"
                },
                toFileStrings(tasks));
    }

    private String[] toFileStrings(Task[] tasks) {
        return Arrays.stream(tasks).map(Task::toFileString).toArray(String[]::new);
    }
}

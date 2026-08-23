package mono;
import mono.exception.NonExistentException;
import mono.task.Task;
import java.util.ArrayList;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

/**
 * Stores Mono's tasks and performs operations requested by tools.
 */
public class MonoBot {
    private final ArrayList<Task> tasks;
    private int messageCount = 0;
    private static final String FILE_PATH = "./data/tasks.txt";

    /**
     * Creates an empty chatbot task list.
     */
    public MonoBot() {
        this.tasks = new ArrayList<>();
        try {
            ensureStorageFile();
        } catch (IOException e) {
            System.out.println("Unable to create task file: " + e.getMessage());
        }
    }

    /**
     * Adds a validated task to the list.
     *
     * @param task validated task to add
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        this.messageCount += 1;

        appendTask(task);

        System.out.print(
                "____________________________________________________________\n" +
                        "Got it. I've added this task:\n" +
                        task + "\n" +
                        "Now you have " + this.tasks.size() + " tasks in the list.\n" +
                        "____________________________________________________________\n"
        );
    }

    /**
     * Appends one newly added task to the existing save file.
     *
     * @param task task to append
     */
    private void appendTask(Task task) {
        try {
            ensureStorageFile();
            try (FileWriter writer = new FileWriter(MonoBot.FILE_PATH, true)) {
                writer.write(task.toFileString());
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Unable to append task: " + e.getMessage());
        }
    }

    /**
     * Creates Mono's data directory and task file when they do not exist.
     *
     * @throws IOException if the directory or file cannot be created
     */
    private void ensureStorageFile() throws IOException {
        File file = new File(MonoBot.FILE_PATH);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()
                && !parent.mkdirs() && !parent.isDirectory()) {
            throw new IOException("Unable to create directory " + parent);
        }

        if (!file.exists() && !file.createNewFile()) {
            throw new IOException("Unable to create file " + file);
        }
    }

    /**
     * Verifies that a one-based task ID refers to a task in the list.
     *
     * @param id one-based task ID
     * @throws NonExistentException if no task has the given ID
     */
    private void validateTaskId(int id) throws NonExistentException {
        if (id < 1 || id > this.tasks.size()) {
            throw new NonExistentException("Task " + id + " does not exist.");
        }
    }

    /**
     * Deletes a task using its one-based list ID.
     *
     * @param id one-based task ID
     * @throws NonExistentException if no task has the given ID
     */
    public void delete(int id) throws NonExistentException {
        validateTaskId(id);
        Task task = this.tasks.get(id - 1);
        this.tasks.remove(id - 1);
        this.messageCount -= 1;

        System.out.print(
                "____________________________________________________________\n" +
                        "Noted. I've removed this task:\n" +
                        task + "\n" +
                        "Now you have " + this.tasks.size() + " tasks in the list.\n" +
                        "____________________________________________________________\n"
        );
    }

    /**
     * Prints every task currently stored by Mono.
     */
    public void listTasks() {
        System.out.print(
                """
                        ____________________________________________________________
                        Here are the tasks in your list:
                        """
        );
        for (int i = 0; i < this.messageCount; i++) {
            Task task = this.tasks.get(i);
            String item = String.format("%d.%s", i + 1, task);
            System.out.println(item);
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Marks a task as completed.
     *
     * @param id one-based task ID
     * @throws NonExistentException if no task has the given ID
     */
    public void markTaskDone(int id) throws NonExistentException {
        validateTaskId(id);
        Task task = this.tasks.get(id - 1);
        task.markDone();
        System.out.print(
                """
                        ____________________________________________________________
                        Nice! I've marked this task as done:
                        """
                        + task + "\n" +
                        "____________________________________________________________\n"
        );
    }

    /**
     * Marks a task as not completed.
     *
     * @param id one-based task ID
     * @throws NonExistentException if no task has the given ID
     */
    public void unmarkTaskDone(int id) throws NonExistentException {
        validateTaskId(id);
        Task task = this.tasks.get(id - 1);
        task.unmarkDone();
        System.out.print(
                """
                        ____________________________________________________________
                        OK, I've marked this task as not done yet:
                        """
                        + task + "\n" +
                        "____________________________________________________________\n"
        );
    }
    /**
     * Prints Mono's banner and greeting message.
     */
    public void greet() {
        String banner = "███╗   ███╗ ██████╗ ███╗   ██╗ ██████╗ \n" +
                "████╗ ████║██╔═══██╗████╗  ██║██╔═══██╗\n" +
                "██╔████╔██║██║   ██║██╔██╗ ██║██║   ██║\n" +
                "██║╚██╔╝██║██║   ██║██║╚██╗██║██║   ██║\n" +
                "██║ ╚═╝ ██║╚██████╔╝██║ ╚████║╚██████╔╝\n" +
                "╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝ ╚═════╝ ";
        String greetingMessage = String.format(
                "____________________________________________________________\n" +
                        "%s\n" +
                        "Hello! I'm Mono.\n" +
                        "What can I do for you?\n" +
                        "____________________________________________________________\n", banner);
        System.out.print(greetingMessage);
    }

    /**
     * Prints Mono's farewell message.
     */
    public void exit() {
        String exitMessage =
                "____________________________________________________________\n"+
                        "Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________\n";
        System.out.print(exitMessage);
    }
}

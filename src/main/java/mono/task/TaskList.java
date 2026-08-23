package mono.task;

import mono.exception.NonExistentException;
import mono.storage.Storage;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Owns Mono's task collection and all operations that change or display it.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private final Storage storage;

    /**
     * Creates a task list initialized from persisted tasks.
     */
    public TaskList() {
        this.storage = new Storage();
        this.tasks = new ArrayList<>(Arrays.asList(this.storage.readStorage()));
    }

    /**
     * Adds and persists a task.
     *
     * @param task task to add
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        saveTasks();
        System.out.print(
                "____________________________________________________________\n" +
                        "Got it. I've added this task:\n" +
                        task + "\n" +
                        "Now you have " + this.tasks.size() + " tasks in the list.\n" +
                        "____________________________________________________________\n"
        );
    }

    /**
     * Deletes a task using a one-based ID.
     *
     * @param id one-based task ID
     * @throws NonExistentException if the ID is invalid
     */
    public void delete(int id) throws NonExistentException {
        validateTaskId(id);
        Task task = this.tasks.remove(id - 1);
        saveTasks();
        System.out.print(
                "____________________________________________________________\n" +
                        "Noted. I've removed this task:\n" +
                        task + "\n" +
                        "Now you have " + this.tasks.size() + " tasks in the list.\n" +
                        "____________________________________________________________\n"
        );
    }

    /**
     * Prints the current tasks with one-based IDs.
     */
    public void listTasks() {
        System.out.print(
                """
                        ____________________________________________________________
                        Here are the tasks in your list:
                        """
        );
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println(String.format("%d.%s", i + 1, this.tasks.get(i)));
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Marks a task as completed and persists the change.
     *
     * @param id one-based task ID
     * @throws NonExistentException if the ID is invalid
     */
    public void markTaskDone(int id) throws NonExistentException {
        validateTaskId(id);
        Task task = this.tasks.get(id - 1);
        task.markDone();
        saveTasks();
        System.out.print(
                "____________________________________________________________\n" +
                        "Nice! I've marked this task as done:\n" +
                        task + "\n" +
                        "____________________________________________________________\n"
        );
    }

    /**
     * Marks a task as incomplete and persists the change.
     *
     * @param id one-based task ID
     * @throws NonExistentException if the ID is invalid
     */
    public void unmarkTaskDone(int id) throws NonExistentException {
        validateTaskId(id);
        Task task = this.tasks.get(id - 1);
        task.unmarkDone();
        saveTasks();
        System.out.print(
                "____________________________________________________________\n" +
                        "OK, I've marked this task as not done yet:\n" +
                        task + "\n" +
                        "____________________________________________________________\n"
        );
    }

    /**
     * Checks a one-based task ID.
     *
     * @param id one-based task ID
     * @throws NonExistentException if no task has that ID
     */
    private void validateTaskId(int id) throws NonExistentException {
        if (id < 1 || id > this.tasks.size()) {
            throw new NonExistentException("Task " + id + " does not exist.");
        }
    }

    /**
     * Persists the complete current task collection.
     */
    private void saveTasks() {
        this.storage.saveTasks(this.tasks.toArray(new Task[0]));
    }
}

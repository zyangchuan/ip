package mono;
import mono.exception.NonExistentException;
import mono.storage.Storage;
import mono.task.Task;

import java.util.ArrayList;

/**
 * Stores Mono's tasks and performs operations requested by tools.
 */
public class MonoBot {
    private final ArrayList<Task> tasks;
    private final Storage storage;
    private int messageCount = 0;

    /**
     * Creates an empty chatbot task list.
     */
    public MonoBot() {
        this.storage = new Storage();
        Task[] storedTasks = this.storage.readStorage();
        this.tasks = new ArrayList<>(java.util.Arrays.asList(storedTasks));
        this.messageCount = storedTasks.length;
    }

    /**
     * Adds a validated task to the list.
     *
     * @param task validated task to add
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        this.messageCount += 1;

        this.storage.saveTasks(this.tasks.toArray(new Task[0]));

        System.out.print(
                "____________________________________________________________\n" +
                        "Got it. I've added this task:\n" +
                        task + "\n" +
                        "Now you have " + this.tasks.size() + " tasks in the list.\n" +
                        "____________________________________________________________\n"
        );
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
        this.storage.saveTasks(this.tasks.toArray(new Task[0]));

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
        this.storage.saveTasks(this.tasks.toArray(new Task[0]));
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
        this.storage.saveTasks(this.tasks.toArray(new Task[0]));
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

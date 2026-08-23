package mono;
import mono.exception.NonExistentException;
import mono.task.Task;
import mono.task.TaskList;

/**
 * Stores Mono's tasks and performs operations requested by tools.
 */
public class MonoBot {
    private final TaskList taskList;

    /**
     * Creates an empty chatbot task list.
     */
    public MonoBot() {
        this.taskList = new TaskList();
    }

    /**
     * Adds a validated task to the list.
     *
     * @param task validated task to add
     */
    public void addTask(Task task) {
        this.taskList.addTask(task);
    }

    /**
     * Deletes a task using its one-based list ID.
     *
     * @param id one-based task ID
     * @throws NonExistentException if no task has the given ID
     */
    public void delete(int id) throws NonExistentException {
        this.taskList.delete(id);
    }

    /**
     * Prints every task currently stored by Mono.
     */
    public void listTasks() {
        this.taskList.listTasks();
    }

    /**
     * Marks a task as completed.
     *
     * @param id one-based task ID
     * @throws NonExistentException if no task has the given ID
     */
    public void markTaskDone(int id) throws NonExistentException {
        this.taskList.markTaskDone(id);
    }

    /**
     * Marks a task as not completed.
     *
     * @param id one-based task ID
     * @throws NonExistentException if no task has the given ID
     */
    public void unmarkTaskDone(int id) throws NonExistentException {
        this.taskList.unmarkTaskDone(id);
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

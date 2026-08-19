package mono;
import mono.exception.WrongFormatException;
import mono.exception.NonExistentException;
import mono.task.*;
import java.util.ArrayList;

public class MonoBot {
    private final ArrayList<Task> tasks;
    private Boolean[] marked;
    private int messageCount = 0;

    public MonoBot() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task described by the user's command.
     *
     * @param input task command entered by the user
     * @throws WrongFormatException if a todo, deadline, or event command is malformed
     */
    public void add(String input) throws WrongFormatException {
        Task task;

        if (input.equals("todo") || input.startsWith("todo ")) {
            if (input.equals("todo")) {
                throw new WrongFormatException("Todo format: todo <description>");
            }
            String name = input.substring("todo ".length()).trim();
            if (name.isEmpty()) {
                throw new WrongFormatException("Todo format: todo <description>");
            }
            task = new ToDo(name);
        } else if (input.equals("deadline") || input.startsWith("deadline ")) {
            int datetimeIndex = input.lastIndexOf("/by");
            if (datetimeIndex <= 9 || input.substring(datetimeIndex + 3).trim().isEmpty()) {
                throw new WrongFormatException(
                        "Deadline format: deadline <description> /by <date/time>");
            }
            String name = input.substring(9, datetimeIndex - 1);
            String datetime = input.substring(datetimeIndex + 4);
            task = new Deadline(name, datetime);
        } else if (input.equals("event") || input.startsWith("event ")) {
            int fromIndex = input.lastIndexOf("/from");
            int toIndex = input.lastIndexOf("/to");
            if (fromIndex <= 6 || toIndex <= fromIndex + 6
                    || input.substring(toIndex + 3).trim().isEmpty()) {
                throw new WrongFormatException(
                        "Event format: event <description> /from <start> /to <end>");
            }
            String name = input.substring(6, fromIndex - 1);
            String startDatetime = input.substring(fromIndex + 6, toIndex - 1);
            String endDatetime = input.substring(toIndex + 4);
            task = new Event(name, startDatetime, endDatetime);
        } else {
            task = new Task(input);
        }

        this.tasks.add(task);
        this.messageCount += 1;

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

    public void list() {
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

    public void exit() {
        String exitMessage =
                "____________________________________________________________\n"+
                        "Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________\n";
        System.out.print(exitMessage);
    }
}

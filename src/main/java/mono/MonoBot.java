package mono;
import mono.task.*;
import java.util.ArrayList;

public class MonoBot {
    private final ArrayList<Task> tasks;
    private Boolean[] marked;
    private int messageCount = 0;

    public MonoBot() {
        this.tasks = new ArrayList<>();
    }

    public void add(String input) {
        System.out.print(
                "____________________________________________________________\n" +
                        "added: " + input + "\n" +
                        "____________________________________________________________\n"
        );
        Task task = new Task(input);
        this.tasks.add(task);
        this.messageCount += 1;
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

    public void markTaskDone(int id) {
        Task task = this.tasks.get(id - 1);
        task.markDone();
        System.out.print(
                """
                        ____________________________________________________________
                        Nice! I've marked this task as done:
                        """
                + task + "\n"
        );
    }

    public void unmarkTaskDone(int id) {
        Task task = this.tasks.get(id - 1);
        task.unmarkDone();
        System.out.print(
                """
                        ____________________________________________________________
                        OK, I've marked this task as not done yet:
                        """
                        + task + "\n"
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

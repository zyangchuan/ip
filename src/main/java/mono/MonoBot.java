package mono;
import mono.Task;

public class MonoBot {
    private Task[] history;
    private Boolean[] marked;
    private int messageCount = 0;

    public MonoBot() {
        this.history = new Task[100];
    }

    public void add(String input) {
        System.out.print(
                "____________________________________________________________\n" +
                        "added: " + input + "\n" +
                        "____________________________________________________________\n"
        );
        Task task = new Task(input);
        this.history[messageCount] = task;
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
            Task task = this.history[i];
            String item = String.format("%d.%s", i + 1, task);
            System.out.println(item);
        }
        System.out.println("____________________________________________________________");
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

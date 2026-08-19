package mono;

public class MonoBot {
    private String[] history;
    private int messageCount = 0;

    public MonoBot() {
        this.history = new String[100];
    }

    public void add(String input) {
        System.out.println(
                "____________________________________________________________\n" +
                        "added: " + input + "\n" +
                        "____________________________________________________________\n"
        );
        String message = String.format("%d. %s", ++this.messageCount, input);
        this.history[messageCount - 1] = message;
    }

    public void list() {
        System.out.println("____________________________________________________________\n");
        for (int i = 0; i < this.messageCount; i++) {
            System.out.println(this.history[i]);
        }
        System.out.println("____________________________________________________________\n");
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
        System.out.println(greetingMessage);
    }

    public void exit() {
        String exitMessage =
                "____________________________________________________________\n"+
                        "Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________\n";
        System.out.println(exitMessage);
    }
}

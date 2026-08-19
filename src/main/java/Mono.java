import java.util.Scanner;

public class Mono {
    public static void main(String[] args) {
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

        String exitMessage =
                        "____________________________________________________________\n"+
                        "Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________\n";

        System.out.println(greetingMessage);

        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                String input = "";
                input = scanner.nextLine();

                if (input.equals("bye")) {
                    System.out.println(exitMessage);
                    break;
                }

                System.out.println(
                                "____________________________________________________________\n" +
                                input + "\n" +
                                "____________________________________________________________\n"
                );
            }
        } finally {
            scanner.close();
        }
    }
}

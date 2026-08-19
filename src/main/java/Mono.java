import mono.MonoBot;
import mono.exception.*;
import java.util.Scanner;

public class Mono {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        MonoBot bot = new MonoBot();

        bot.greet();

        try {
            while (true) {
                String input = "";
                input = scanner.nextLine();

                try {
                    if (input.isEmpty()) {
                        throw new EmptyCommandException("Error: Command cannot be empty!");
                    }

                    if (input.equals("bye")) {
                        bot.exit();
                        break;
                    }

                    if (input.equals("list")) {
                        bot.list();
                    } else if (input.startsWith("mark ")) {
                        int id = Integer.parseInt(input.substring(5));
                        bot.markTaskDone(id);
                    } else if (input.startsWith("unmark ")) {
                        int id = Integer.parseInt(input.substring(7));
                        bot.unmarkTaskDone(id);
                    } else if (input.startsWith("todo ") || input.startsWith("deadline ")
                            || input.startsWith("event ")) {
                        bot.add(input);
                    } else {
                        throw new UnknownCommandException("Error: Command is unknown.");
                    }

                } catch (MonoException e) {
                    System.out.print(
                            "____________________________________________________________\n" +
                                    e.getMessage() + "\n" +
                                    "____________________________________________________________\n"
                    );
                }
            }
        } finally {
            scanner.close();
        }
    }
}

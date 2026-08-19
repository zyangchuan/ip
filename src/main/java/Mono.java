import mono.MonoBot;
import mono.exception.*;
import java.util.Scanner;

public class Mono {
    /**
     * Parses the numeric task ID following a task-management command.
     *
     * @param input complete command entered by the user
     * @param command command whose argument is being parsed
     * @return the positive task ID
     * @throws WrongFormatException if the argument is not a positive integer
     */
    private static int parseTaskId(String input, String command) throws WrongFormatException {
        String argument = input.substring(command.length()).trim();
        try {
            int id = Integer.parseInt(argument);
            if (id <= 0) {
                throw new WrongFormatException(command + " requires a positive integer task ID.");
            }
            return id;
        } catch (NumberFormatException e) {
            throw new WrongFormatException(command + " requires a positive integer task ID.");
        }
    }

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
                    } else if (input.equals("mark") || input.startsWith("mark ")) {
                        int id = parseTaskId(input, "mark");
                        bot.markTaskDone(id);
                    } else if (input.equals("unmark") || input.startsWith("unmark ")) {
                        int id = parseTaskId(input, "unmark");
                        bot.unmarkTaskDone(id);
                    } else if (input.startsWith("todo ") || input.startsWith("deadline ")
                            || input.startsWith("event ")) {
                        bot.add(input);
                    } else if (input.equals("delete") || input.startsWith("delete ")) {
                        int id = parseTaskId(input, "delete");
                        bot.delete(id);
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

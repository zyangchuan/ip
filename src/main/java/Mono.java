import mono.MonoBot;
import mono.CommandType;
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
            commandLoop:
            while (true) {
                String input = scanner.nextLine().trim();

                try {
                    if (input.isEmpty()) {
                        throw new EmptyCommandException("Error: Command cannot be empty!");
                    }

                    CommandType commandType = CommandType.fromInput(input);
                    if (commandType == null) {
                        throw new UnknownCommandException("Error: Command is unknown.");
                    }

                    switch (commandType) {
                    case BYE:
                        bot.exit();
                        break commandLoop;
                    case LIST:
                        bot.list();
                        break;
                    case MARK:
                        bot.markTaskDone(parseTaskId(input, commandType.getKeyword()));
                        break;
                    case UNMARK:
                        bot.unmarkTaskDone(parseTaskId(input, commandType.getKeyword()));
                        break;
                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        bot.add(input);
                        break;
                    case DELETE:
                        bot.delete(parseTaskId(input, commandType.getKeyword()));
                        break;
                    default:
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

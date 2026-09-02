import java.util.Scanner;

import mono.MonoBot;
import mono.exception.MonoException;
import mono.parser.InputParser;
import mono.tool.Tool;
import mono.tool.ToolRegistry;
import mono.tool.ToolSignal;

/**
 * Starts Mono and dispatches user input to registered tools.
 */
public class Mono {
    /** Creates an application entry point. */
    public Mono() {
    }

    /**
     * Runs the chatbot's input loop.
     *
     * @param args command-line arguments, which Mono does not use
     */
    public static void main(String[] args) {
        MonoBot bot = new MonoBot();
        ToolRegistry registry = new ToolRegistry();
        bot.greet();

        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                try {
                    InputParser.ToolInput input = InputParser.parse(scanner.nextLine());
                    Tool tool = registry.get(input.name());
                    ToolSignal signal = tool.invoke(input.arguments(), bot);
                    if (signal == ToolSignal.EXIT) {
                        break;
                    }
                } catch (MonoException e) {
                    printError(e);
                }
            }
        }
    }

    /**
     * Prints an exception message using Mono's standard response format.
     *
     * @param exception exception to display
     */
    private static void printError(MonoException exception) {
        System.out.print(
                "____________________________________________________________\n"
                        + exception.getMessage() + "\n"
                        + "____________________________________________________________\n"
        );
    }
}

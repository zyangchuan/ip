import mono.MonoBot;
import mono.exception.MonoException;
import mono.parser.InputParser;
import mono.tool.Tool;
import mono.tool.ToolRegistry;
import mono.tool.ToolSignal;

import java.util.Scanner;

/**
 * Starts Mono and dispatches user input to registered tools.
 */
public class Mono {
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
            boolean shouldExit = false;
            while (scanner.hasNextLine() && !shouldExit) {
                try {
                    InputParser.ToolInput input = InputParser.parse(scanner.nextLine());
                    Tool tool = registry.get(input.name());
                    ToolSignal signal = tool.invoke(input.arguments(), bot);
                    shouldExit = signal == ToolSignal.EXIT;
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
                "____________________________________________________________\n" +
                        exception.getMessage() + "\n" +
                        "____________________________________________________________\n"
        );
    }
}

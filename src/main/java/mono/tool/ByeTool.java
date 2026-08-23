package mono.tool;

import mono.MonoBot;
import mono.exception.MonoException;
import mono.exception.WrongFormatException;

/**
 * Ends the Mono session.
 */
public class ByeTool implements Tool {
    @Override
    public ToolSignal invoke(String arguments, MonoBot bot) throws MonoException {
        if (!arguments.isEmpty()) {
            throw new WrongFormatException("bye does not accept arguments.");
        }
        bot.exit();
        return ToolSignal.EXIT;
    }
}

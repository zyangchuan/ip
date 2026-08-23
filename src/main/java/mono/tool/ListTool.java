package mono.tool;

import mono.MonoBot;
import mono.exception.MonoException;
import mono.exception.WrongFormatException;

/**
 * Displays all tasks currently stored by Mono.
 */
public class ListTool implements Tool {
    @Override
    public ToolSignal invoke(String arguments, MonoBot bot) throws MonoException {
        if (!arguments.isEmpty()) {
            throw new WrongFormatException("list does not accept arguments.");
        }
        bot.listTasks();
        return ToolSignal.CONTINUE;
    }
}

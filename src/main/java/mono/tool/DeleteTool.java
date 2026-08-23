package mono.tool;

import mono.MonoBot;
import mono.exception.MonoException;
import mono.parser.TaskIdParser;

/**
 * Deletes a task by its one-based list ID.
 */
public class DeleteTool implements Tool {
    @Override
    public ToolSignal invoke(String arguments, MonoBot bot) throws MonoException {
        bot.delete(TaskIdParser.parse(arguments));
        return ToolSignal.CONTINUE;
    }
}

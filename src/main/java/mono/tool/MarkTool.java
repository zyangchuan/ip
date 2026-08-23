package mono.tool;

import mono.MonoBot;
import mono.exception.MonoException;
import mono.parser.TaskIdParser;

/**
 * Marks a task as completed.
 */
public class MarkTool implements Tool {
    @Override
    public ToolSignal invoke(String arguments, MonoBot bot) throws MonoException {
        bot.markTaskDone(TaskIdParser.parse(arguments));
        return ToolSignal.CONTINUE;
    }
}

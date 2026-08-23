package mono.tool;

import mono.MonoBot;
import mono.exception.MonoException;
import mono.parser.TaskIdParser;

/**
 * Marks a task as not completed.
 */
public class UnmarkTool implements Tool {
    @Override
    public ToolSignal invoke(String arguments, MonoBot bot) throws MonoException {
        bot.unmarkTaskDone(TaskIdParser.parse(arguments));
        return ToolSignal.CONTINUE;
    }
}

package mono.tool;

import mono.MonoBot;
import mono.exception.MonoException;
import mono.exception.WrongFormatException;

/**
 * Finds tasks whose names contain a keyword.
 */
public class FindTasksTool implements Tool {
    @Override
    public ToolSignal invoke(String arguments, MonoBot bot) throws MonoException {
        String keyword = arguments.trim();
        if (keyword.isEmpty()) {
            throw new WrongFormatException("Find format: find <keyword>");
        }
        bot.findTasks(keyword);
        return ToolSignal.CONTINUE;
    }
}

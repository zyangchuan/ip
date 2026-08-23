package mono.tool;

import mono.MonoBot;
import mono.exception.MonoException;
import mono.exception.WrongFormatException;
import mono.task.ToDo;

/**
 * Creates a to-do task from a description.
 */
public class TodoTool implements Tool {
    @Override
    public ToolSignal invoke(String arguments, MonoBot bot) throws MonoException {
        String description = arguments.trim();
        if (description.isEmpty()) {
            throw new WrongFormatException("Todo format: todo <description>");
        }
        bot.addTask(new ToDo(description));
        return ToolSignal.CONTINUE;
    }
}

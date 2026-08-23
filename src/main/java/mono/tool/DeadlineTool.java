package mono.tool;

import mono.MonoBot;
import mono.exception.MonoException;
import mono.exception.WrongFormatException;
import mono.task.Deadline;

/**
 * Creates a deadline task from a description and due date.
 */
public class DeadlineTool implements Tool {
    private static final String BY_MARKER = " /by ";

    @Override
    public ToolSignal invoke(String arguments, MonoBot bot) throws MonoException {
        int byIndex = arguments.lastIndexOf(BY_MARKER);
        if (byIndex <= 0) {
            throw new WrongFormatException(
                    "Deadline format: deadline <description> /by <date/time>");
        }

        String description = arguments.substring(0, byIndex).trim();
        String datetime = arguments.substring(byIndex + BY_MARKER.length()).trim();
        if (description.isEmpty() || datetime.isEmpty()) {
            throw new WrongFormatException(
                    "Deadline format: deadline <description> /by <date/time>");
        }

        bot.addTask(new Deadline(description, datetime));
        return ToolSignal.CONTINUE;
    }
}

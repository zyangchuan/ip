package mono.tool;

import mono.MonoBot;
import mono.exception.MonoException;
import mono.exception.WrongFormatException;
import mono.task.Deadline;

import java.time.format.DateTimeParseException;

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
                    "Deadline format: deadline <description> /by <date>");
        }

        String description = arguments.substring(0, byIndex).trim();
        String dateText = arguments.substring(byIndex + BY_MARKER.length()).trim();
        if (description.isEmpty() || dateText.isEmpty()) {
            throw new WrongFormatException(
                    "Deadline format: deadline <description> /by <date>");
        }

        try {
            bot.addTask(new Deadline(description, dateText));
        } catch (DateTimeParseException e) {
            throw new WrongFormatException(
                    "Deadline date must use the format yyyy-MM-dd (for example, 2019-10-15)");
        }
        return ToolSignal.CONTINUE;
    }
}

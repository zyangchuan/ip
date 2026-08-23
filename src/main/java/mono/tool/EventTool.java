package mono.tool;

import mono.MonoBot;
import mono.exception.MonoException;
import mono.exception.WrongFormatException;
import mono.task.Event;

/**
 * Creates an event task from a description, start time, and end time.
 */
public class EventTool implements Tool {
    private static final String FROM_MARKER = " /from ";
    private static final String TO_MARKER = " /to ";

    @Override
    public ToolSignal invoke(String arguments, MonoBot bot) throws MonoException {
        int fromIndex = arguments.indexOf(FROM_MARKER);
        int toIndex = arguments.indexOf(TO_MARKER);
        if (fromIndex <= 0 || toIndex <= fromIndex + FROM_MARKER.length()) {
            throw new WrongFormatException(
                    "Event format: event <description> /from <start> /to <end>");
        }

        String description = arguments.substring(0, fromIndex).trim();
        String startDatetime = arguments
                .substring(fromIndex + FROM_MARKER.length(), toIndex).trim();
        String endDatetime = arguments.substring(toIndex + TO_MARKER.length()).trim();
        if (description.isEmpty() || startDatetime.isEmpty() || endDatetime.isEmpty()) {
            throw new WrongFormatException(
                    "Event format: event <description> /from <start> /to <end>");
        }

        bot.addTask(new Event(description, startDatetime, endDatetime));
        return ToolSignal.CONTINUE;
    }
}

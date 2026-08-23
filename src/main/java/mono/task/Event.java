package mono.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * A task that takes place between two calendar dates.
 */
public class Event extends Task {
    private static final DateTimeFormatter DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Creates an event from ISO-8601 date strings.
     *
     * @param name event description
     * @param startDateText start date in {@code yyyy-MM-dd} format
     * @param endDateText end date in {@code yyyy-MM-dd} format
     * @throws java.time.format.DateTimeParseException if either date is invalid
     */
    public Event(String name, String startDateText, String endDateText) {
        super(name);
        this.startDate = LocalDate.parse(startDateText, DateTimeFormatter.ISO_LOCAL_DATE);
        this.endDate = LocalDate.parse(endDateText, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * Returns the event's stable, pipe-delimited save-file representation.
     *
     * @return task type, completion status, name, start time, and end time
     */
    @Override
    public String toFileString() {
        return String.format(
                "E | %d | %s | %s | %s",
                super.isDone ? 1 : 0,
                super.name,
                this.startDatetime,
                this.endDatetime);
    }

    public String toString() {
        return String.format(
                "[E][%s] %s (from: %s to: %s)",
                super.isDone ? "X" : " ",
                super.name,
                this.startDate.format(DISPLAY_FORMAT),
                this.endDate.format(DISPLAY_FORMAT));
    }
}

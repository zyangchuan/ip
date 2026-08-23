package mono.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * A task that must be completed by a specific calendar date.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
    private final LocalDate date;

    /**
     * Creates a deadline from an ISO-8601 date string.
     *
     * @param name deadline description
     * @param dateText date in {@code yyyy-MM-dd} format
     * @throws java.time.format.DateTimeParseException if the date is invalid
     */
    public Deadline(String name, String dateText) {
        super(name);
        this.date = LocalDate.parse(dateText, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * Returns the deadline's stable, pipe-delimited save-file representation.
     *
     * @return task type, completion status, name, and deadline
     */
    @Override
    public String toFileString() {
        return String.format("D | %d | %s | %s", super.isDone ? 1 : 0, super.name, this.date);
    }

    public String toString() {
        return String.format(
                "[D][%s] %s (by: %s)",
                super.isDone ? "X" : " ",
                super.name,
                this.date.format(DISPLAY_FORMAT));
    }
}

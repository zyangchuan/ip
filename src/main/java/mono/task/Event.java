package mono.task;

public class Event extends Task {
    private final String startDatetime;
    private final String endDatetime;

    public Event(String name, String startDatetime, String endDatetime) {
        super(name);
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
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
                "[T][%s] %s (from: %s to: %s)",
                super.isDone ? "X" : " ",
                super.name,
                this.startDatetime,
                this.endDatetime);
    }
}

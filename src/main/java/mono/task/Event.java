package mono.task;

public class Event extends Task {
    private final String startDatetime;
    private final String endDatetime;

    public Event(String name, String startDatetime, String endDatetime) {
        super(name);
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
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
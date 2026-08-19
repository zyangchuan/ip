package mono.task;

public class Deadline extends Task {
    private final String datetime;

    public Deadline(String name, String datetime) {
        super(name);
        this.datetime = datetime;
    }

    public String toString() {
        return String.format("[T][%s] %s (by: %s)", super.isDone ? "X" : " ", super.name, this.datetime);
    }
}

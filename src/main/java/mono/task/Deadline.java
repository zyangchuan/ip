package mono.task;

public class Deadline extends Task {
    private final String datetime;

    public Deadline(String name, String datetime) {
        super(name);
        this.datetime = datetime;
    }

    /**
     * Returns the deadline's stable, pipe-delimited save-file representation.
     *
     * @return task type, completion status, name, and deadline
     */
    @Override
    public String toFileString() {
        return String.format("D | %d | %s | %s", super.isDone ? 1 : 0, super.name, this.datetime);
    }

    public String toString() {
        return String.format("[T][%s] %s (by: %s)", super.isDone ? "X" : " ", super.name, this.datetime);
    }
}

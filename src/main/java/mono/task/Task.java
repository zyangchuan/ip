package mono.task;

public class Task {
    protected final String name;
    protected Boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Returns the stable, pipe-delimited representation used in Mono's save file.
     *
     * @return task type, completion status, and task name
     */
    public String toFileString() {
        return String.format("T | %d | %s", this.isDone ? 1 : 0, this.name);
    }

    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.name);
    }
}

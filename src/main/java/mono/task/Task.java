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

    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.name);
    }
}

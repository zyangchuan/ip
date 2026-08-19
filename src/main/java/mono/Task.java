package mono;

public class Task {
    private String name;
    private Boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public String toString() {
        return String.format("[%s] %s", this.done ? "X" : " ", this.name);
    }
}

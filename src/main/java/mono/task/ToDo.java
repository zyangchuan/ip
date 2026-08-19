package mono.task;

class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    public String toString() {
        return String.format("[T][%s] %s", super.isDone ? "X" : " ", super.name);
    }
}

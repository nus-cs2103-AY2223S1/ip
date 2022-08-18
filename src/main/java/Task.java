public class Task {
    private boolean isDone;
    private String name;

    Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }


    void mark() {
        this.isDone = true;
    }

    void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.name;
    }
}

public class Task {
    private final String description;
    private final int index;
    private boolean isDone;

    public Task(int index, String description) {
        this.index = index;
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String toStringWithIndex() {
        return index + ".[" + this.getStatusIcon() + "] " + description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
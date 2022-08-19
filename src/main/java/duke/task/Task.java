package duke.task;

public abstract class Task {

    protected final String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
        isDone = false;
    }

    protected String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public abstract String encodeToString();
}
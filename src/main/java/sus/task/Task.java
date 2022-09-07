package sus.task;

/**
 * Represents a task.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
        isDone = false;
    }

    protected String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract String encodeToString();
}

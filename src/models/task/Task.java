package models.task;

/**
 * Data class to represent a Task and output a user-friendly String representation
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    protected String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    abstract String getTypeIndicator();

    @Override
    public String toString() {
        return String.format("[%s] [%s] %s", getTypeIndicator(), getStatusIcon(), description);
    }
}

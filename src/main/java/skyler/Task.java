package skyler;

/**
 * Represents a generic task
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task object
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    public abstract String toStringUnformatted();

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}

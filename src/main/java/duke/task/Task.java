package duke.task;

/**
 * Defines general characteristics of a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task object.
     * @param description details of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String toStringForStorage() {
        return this.getStatusIcon() + "|" + this.description;
    }

    //@inheritdoc
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

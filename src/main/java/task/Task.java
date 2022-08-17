package task;

/**
 * The Task class encapsulates a task.
 * A task has a string description describing the task to be done, and a boolean indicator to represent
 * the status of completion of the task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    public Task(String description, TaskType type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    /**
     * Returns an icon representing the state of completion of the task.
     *
     * @return A checkmark icon (✓) if the task is done, and " " otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task
     */
    public String toString() {
        return String.format("[%s][%s] %s", this.type.getAbbreviation(),
                this.getStatusIcon(), this.description);
    }
}

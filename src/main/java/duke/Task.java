package duke;

/**
 * Abstract Task class.
 */
public abstract class Task {

    protected boolean isDone;
    protected String description;

    /**
     * Constructor for the Task Class.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns icon representing task status.
     *
     * @return A String representing the status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns string format of task to be saved in the file.
     * @return String representation to be stored in the file.
     */
    public abstract String saveTaskAsString();

    /**
     * @inheritDoc
     * @return String representation with status icon and description.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

}

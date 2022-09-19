package task;

/**
 * Encapsulates a task that need to be done by the user.
 *
 * @author Marcus Low
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task instance.
     *
     * @param description The description of the task.
     * @param isDone Whether the task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Mark the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Mark the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     * @return A string of the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the task in a text format to be saved to a local file.
     * @return A string representation of the task.
     */
    public abstract String toStringText();
}

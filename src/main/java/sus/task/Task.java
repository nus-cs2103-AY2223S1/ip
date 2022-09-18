package sus.task;

/**
 * Represents a task.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructor.
     *
     * @param description description of the task
     */
    protected Task(String description) {
        this.description = description;
        isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Set status of task.
     *
     * @param isDone status to be set
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Set description of task.
     *
     * @param description description of task to be set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    protected String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Converts task to string to be saved in storage file.
     *
     * @return string to save in
     */
    public abstract String encodeToString();

    @Override
    public String toString() {
        return String.format("%s %s", getStatusIcon(), description);
    }
}

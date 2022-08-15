public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the description of the task
     * @return description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the status of the task
     * @return status of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Mark a task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark a task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}

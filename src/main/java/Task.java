public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs Task with its description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status of the task.
     *
     * @return The status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " " ); // mark done task with X
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Shows the description and status of the task.
     *
     * @return String with the description and status of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + this.getDescription() ;
    }
}

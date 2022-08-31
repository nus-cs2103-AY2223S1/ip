package qoobee;

/**
 * Represents a base task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task given a description.
     * @param description The details of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon as marked or unmarked.
     * @return The status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the details of the task.
     * @return The details of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the String representation of the task.
     * @return The String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Sets the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

}

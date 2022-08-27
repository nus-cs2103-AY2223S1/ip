package Sakura;

/**
 * Abstract representation of a task in the Sakura database.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Default constructor for Task.
     *
     * @param description description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the string description of a task.
     *
     * @return string description of a task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if a task is completed.
     *
     * @return X if a task is completed, empty if not completed.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks a task as completed.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as not completed.
     */
    public void markUndone() {
        this.isDone = false;
    }

    public abstract String stringifyTask();

    /**
     * Returns a string representation of a task description and its completion status.
     *
     * @return String representation of a task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

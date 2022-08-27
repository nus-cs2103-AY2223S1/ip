package piggy.task;

/**
 * Contains information for a task and allows for marking whether it is done.
 */
public class Task {
    /** The description of the task */
    String description;
    /** Whether the task is done */
    boolean isDone;

    /**
     * Creates a new Task with the given description.
     * The task is marked as not done by default.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a status icon based on whether the task is done.
     *
     * @return The {@link String} "X" if done, and " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the Task as done.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks the Task as not done.
     */
    public void markNotDone() {
        isDone = false;
    }

    /**
     * Returns the description of the Task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the current description of the Task
     *
     * @param description The new description to be set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns whether the Task is done.
     *
     * @return true if done, and false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns a String representation of the Task.
     *
     * @return A String of the format "[<X or ' '>] <description>"
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}

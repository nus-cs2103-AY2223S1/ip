package duke.task;

/**
 * Represents a description of what a user has planned to do.
 */
public class Task {
    protected String description;
    protected boolean isCompleted;

    /**
     * Initialises a <code>Task</code> object that represents the task that
     * the user has added. The task is marked incomplete by default.
     * @param name Description of the task.
     */
    public Task(String name) {
        this.description = name;
        this.isCompleted = false;
    }

    /**
     * Returns the status of the task.
     * @return The status of the task.
     */
    public String getStatus() {
        return isCompleted ? "X" : " ";
    }

    /**
     * Marks the task as completed.
     */
    public void markAsCompleted() {
        isCompleted = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markAsNotCompleted() {
        isCompleted = false;
    }

    /**
     * Returns the description of the task.
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status and description of the task.
     * @return The status and description of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatus() + "] "
                + this.description;
    }
}

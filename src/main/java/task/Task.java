package task;

/**
 * Encapsulates a responsiblity to be completed by the user.
 *
 * @author fannyjian
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new instance of a Task with description and marked as uncompleted.
     *
     * @param description Specification of task to be completed.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Indicates whether task is completed.
     *
     * @return State of completion.
     */
    public String getStatusIcon() {
        return (isDone ? "✧" : " "); // mark done task with X
    }

    /**
     * Updates the status of task.
     *
     * @param status New state of completion.
     */
    public void setStatusIcon(boolean status) {
        this.isDone = status;
    }

    /**
     * Returns the string representation of the Task.
     *
     * @return String representation of the Task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}

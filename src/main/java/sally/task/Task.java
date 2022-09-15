package sally.task;

/**
 * Task class to represent generic task
 *
 * @author liviamil
 */

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for tasks
     *
     * @param description description for the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for tasks
     *
     * @param description description for the task
     * @param isDone true if task is marked as done, false otherwise
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the description of a task
     *
     * @return task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the status icon of a task
     *
     * @return task status icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks a task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as undone
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * gets the status of a task
     *
     * @return true if done, false otherwise
     */
    public boolean getDoneStatus() {
        return this.isDone;
    }

    /**
     * Converts task to save to file format
     *
     * @return task in save to file String format
     */
    public String toStorage() {
        return (isDone ? "1" : "0") + " | " + this.description;
    }

    /**
     * Gets the output string for save to file
     *
     * @return output string for save to file
     */
    public String getOutput() {
        return String.format("O | %d | %s", isDone ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

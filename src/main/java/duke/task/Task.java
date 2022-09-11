package duke.task;

/**
 * Abstract class representing a Task for Duke Bot.
 */
public abstract class Task {
    protected String description;
    protected String commandString;
    protected boolean isDone;

    /**
     * Constructor for Task.
     */
    public Task() {
        this.isDone = false;
    }

    /**
     * Gets a string representing whether Task is done or not.
     *
     * @return "X" if task is done, " " if task is not yet done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Gets a string representing whether Task is done or not.
     *
     * @return "mark" if task is done, "unmark" if task is not yet done.
     */
    public String getStatus() {
        return (isDone ? "mark" : "unmark");
    }

    /**
     * Marks current Task as done.
     */
    public void setAsDone() {
        this.isDone = true;
    }

    /**
     * Marks current Task as not done.
     */
    public void setAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns string representation of Task.
     * This representation is formatted with the status icon.
     *
     * @return String representation of Task.
     */
    public String toString() {
        assert description != null;
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns string representation of Task for storage purposes.
     * This string representation is the command used to create and store the Task.
     *
     * @return String representation of Task for storage purposes.
     */
    public String toStorageString() {
        assert commandString != null;
        return commandString + "\n" + getStatus();
    }
}

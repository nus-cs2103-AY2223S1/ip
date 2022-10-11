package chick.task;

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
     * Marks current Task as done.
     *
     * @return Whether task status changed.
     */
    public boolean setAsDone() {
        boolean previousStatus = isDone;
        isDone = true;
        return !previousStatus;
    }

    /**
     * Marks current Task as not done.
     *
     * @return Whether task status changed.
     */
    public boolean setAsUndone() {
        boolean previousStatus = isDone;
        isDone = false;
        return previousStatus;
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
     * Returns description of Task.
     *
     * @return Description string of Task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns string representation of Task for storage purposes.
     * This string representation is the command used to create and store the Task.
     *
     * @return String representation of Task for storage purposes.
     */
    public String toStorageString() {
        assert commandString != null;
        return isDone ? commandString + "\nmark" : commandString;
    }

    @Override
    public boolean equals(Object task) {
        if (task instanceof Task) {
            return ((Task) task).toStorageString().equals(toStorageString());
        }
        return false;
    }
}

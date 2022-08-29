package duke.task;

/**
 * Abstract class representing a Task for Duke Bot.
 */
abstract public class Task {
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
        return (isDone? "mark" : "unmark");
    }

    /**
     * Marks current Task as done.
     */
    public void done() {
        this.isDone = true;
    }

    /**
     * Marks current Task as not done.
     */
    public void undone() {
        this.isDone = false;
    }

    /**
     * Returns string representation of Task.
     * This representation is formatted with the status icon.
     * 
     * @return String representation of Task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns string representation of Task for storage purposes.
     * This string representation is the command used to create and store the Task.
     * 
     * @return String representation of Task for storage purposes.
     */
    public String toStorageString() {
        return commandString + "\n" + getStatus();
    }
}

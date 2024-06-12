package duke;

/**
 * Has description and isDone field to describe and mark completion status of a task.
 *
 * @author Yuvaraj Kumaresan
 */
public class Task {

    /**
     * Describes the task.
     */
    protected String description;

    /**
     * States if the task is done or not.
     */
    protected boolean isDone;

    /**
     * Constructor.
     *
     * @param description The string describing the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the string representation of the marked status of the task.
     *
     * @return String representation of the isDone field.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the isDone field to the boolean value specified.
     *
     * @param done Boolean value depicting if the task is done or not done.
     */
    public void setIsDone(Boolean done) {
        this.isDone = done;
    }

    /**
     * Gets the isDone field.
     *
     * @return Boolean representing if the task is done.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Gets the description of the task
     *
     * @return String with the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Converts task object to its string representation.
     *
     * @return String representation of the task object.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}

package duke.task;

/**
 * The Task class.
 */
public class Task {
    /** Each Task has a description. */
    protected String description;

    /** Each Task can be marked as done or not. */
    protected boolean isDone;

    /**
     * Constructor for a Task object.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns 'X' or " " depending on whether Task is marked done or not.
     *
     * @return Returns X or an empty string depending on whether a Task is marked done.
     */
    public String getDoneStatus() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Sets the isDone variable for the task.
     *
     * @param isDone the new value to update isDone to.
     */
    public void setDoneStatus(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Gets the description of the task.
     *
     * @return Returns the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * String representation of a Task.
     *
     * @return Returns the string representation of a task.
     */
    @Override
    public String toString() {
        return "[" + this.getDoneStatus() + "] " + this.description;
    }
}

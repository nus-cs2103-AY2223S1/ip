package duke.task;

/**
 * The Task class represents a task to be done.
 *
 * @author Edric Yeo
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a Task instance given a description.
     *
     * @param description The description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Method that returns a String representing if a task has been marked as done.
     *
     * @return The String representing if a task has been marked as done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Method that marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Method that marks the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Method that returns the String representation of a Task.
     *
     * @return The String representation of a Task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Method that returns the String representation of a Task,
     * in the format that it is stored (in the data file).
     *
     * @return The String representation of a Task to be stored.
     */
    public String toDataEntry() {
        return "General Task";
    }
}

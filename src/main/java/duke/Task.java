package duke;

/**
 * Represents a task.
 * <b>Note:</b> Every time a task is initialized, its isDone status will be set as false at first.
 *
 * @author Liu Han
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Task Constructor
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Status Getter
     * @return String that represents the status of the task ("X" or " " if it is done or not).
     */
    public String getStatus() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Status Setter
     * <p>
     *     Set the status of the task as done
     * </p>
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Status Setter
     * <p>
     *     Set the status of the task as not done
     * </p>
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Prints the task when list is called by the user
     * @return String in the format <b>[isDone] description</b>.
     */
    @Override
    public String toString() {
        return "[" + getStatus() + "] " + this.description;
    }

    /**
     * A dummy function for subclasses to override
     * @return Empty string.
     */
    public String toSave() {
        return "";
    }
}
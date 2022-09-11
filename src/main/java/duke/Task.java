package duke;

/**
 * Represents a task with its description and
 * its completion status
 *
 * @author  Gerald Teo Jin Wei
 * @version 0.1
 * @since   2022-08-28
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class without
     * specifying completion status
     * @param description Description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for Task class
     * @param description Description of task
     * @param isDone Completion status of task
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the task as completed
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as not completed
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the task's completion status as a string
     * @return String This returns "X" when completed and " " when not completed
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the task's completion status and description
     * @return String This returns the string of the task in the specified format
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}

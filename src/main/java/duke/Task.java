package duke;

/**
 * Represents the main Task class.
 *
 * @author Denzel Tan
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String tag;

    /**
     * Constructor for tasks.
     *
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tag = "";
    }


    /**
     * Gets the status of the task, done or undone.
     *
     * @return whether the task is done or undone
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }


    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }


    /**
     * Unmarks a task as undone.
     */
    public void markUndone() {
        this.isDone = false;
    }


    /**
     * Sets the tag of a task.
     *
     * @param str the tag to set it as
     */
    public void setTag(String str) {
        this.tag = str;
    }


    /**
     * Returns the tag of a task.
     *
     * @return the tag of the specified task
     */
    public String getTag() {
        return this.tag;
    }


    /**
     * toString method of a task.
     *
     * @return the string representation of a task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + this.description;
    }
}

package duke;

/**
 * Represents a skeleton for a Task Class.
 * @author Jason
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object.
     * @param description Description of the task object.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Provides the current "marked" status of a task.
     * @return Marked status of task.
     */
    public String getStatusIcon() {
        //Mark done task with X
        return (isDone ? "X" : " ");
    }

    /**
     * Gets the description of current task.
     * @return Description of task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks current task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks current task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Writes this event task into the save file format.
     * @return String to be stored in save file.
     */
    public abstract String saveData();
}

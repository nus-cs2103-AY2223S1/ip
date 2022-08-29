package task;

/**
 * Represents a Todo task that inherits
 * from Task.
 */
public class Todo extends Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for this Todo task.
     *
     * @param description The Todo task.
     */
    public Todo(String description) {
        this.description = description;
    }

    /**
     * Changes the boolean isDone depending on whether the user
     * wants to mark or unmark the Todo task.
     *
     * @param bool The boolean for whether the Todo task is marked
     *             or unmarked.
     */
    public void isMark(boolean bool) {
        this.isDone = bool;
    }

    /**
     * Returns a string representation of whether
     * the Todo task is done or undone.
     *
     * @return Returns a string representation of whether
     * the Todo task is done or undone.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the string representation of the Todo task.
     *
     * @return Returns the string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " +
                this.description;
    }
}

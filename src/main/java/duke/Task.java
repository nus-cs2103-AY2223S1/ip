package duke;

/**
 * Represents a generic task
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Construct a <code>Task</code>.
     *
     * @param description Description of the task.
     * @param isDone Indicator whether the task has been done or not.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Mark this <code>Task</code> as done.
     *
     * @return True.
     */
    public boolean markAsDone() {
        this.isDone = true;
        return true;
    }

    /**
     * Mark this <code>Task</code> as not done.
     *
     * @return False.
     */
    public boolean markAsUndone() {
        this.isDone = false;
        return false;
    }

    /**
     * Returns a <code>String</code> that indicates whether this <code>Task</code> is done or not.
     * If done, <code>String</code> is "X".
     * If not done, <code>String</code> is " ".
     *
     * @return A <code>String</code> that represents if the <code>Task</code> is done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); //mark done task with X
    }

    /**
     * Returns the description of this <code>Task</code>.
     *
     * @return A description of this <code>Task</code>.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the <code>String</code> representation of this <code>Task</code> and whether it is done or not.
     *
     * @return <code>String</code> representation of this <code>Task</code>.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public abstract String toStorageFormat();
}

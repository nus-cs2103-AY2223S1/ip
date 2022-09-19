package task;

/**
 * Represents all the tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String date;

    /**
     * Changes the boolean isDone depending on whether the user
     * wants to mark or unmark the task.
     *
     * @param bool The boolean for whether the task is marked
     *             or unmarked.
     */
    public void isMark(boolean bool) {
        this.isDone = bool;
    }

    /**
     * Returns the description of the task.
     *
     * @return Returns the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the date for the task.
     *
     * @param str The date of the task.
     */
    public void setDate(String str) {
        this.date = str;
    }

    /**
     * Returns a string representation of whether
     * the task is done or undone.
     *
     * @return Returns a string representation of whether
     * the task is done or undone.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the string representation of the task.
     *
     * @return Returns the string representation of the task.
     */
    public String toString() {
        return this.description;
    }
}

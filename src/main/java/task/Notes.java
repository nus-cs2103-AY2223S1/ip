package task;

public class Notes extends Task {
    protected String description;
    protected final boolean IS_DONE = false;

    /**
     * Constructor for this Notes task.
     *
     * @param description The Notes task.
     */
    public Notes(String description) {
        this.description = description;
    }

    /**
     * Changes the boolean isDone depending on whether the user
     * wants to mark or unmark the Notes task.
     *
     * @param bool The boolean for whether the Notes task is marked
     *             or unmarked.
     */
    public void isMark(boolean bool) {}

    /**
     * Returns a string representation of whether
     * the Notes task is done or undone.
     *
     * @return Returns a string representation of whether
     * the Notes task is done or undone.
     */
    public String getStatusIcon() {
        return (IS_DONE ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the string representation of the Notes task.
     *
     * @return Returns the string representation of the Notes task.
     */
    @Override
    public String toString() {
        return "[N][" + this.getStatusIcon() + "] " +
                this.description;
    }
}

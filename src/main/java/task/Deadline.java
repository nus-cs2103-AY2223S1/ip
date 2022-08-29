package task;

import dateformat.DateFormat;

/**
 * Represents a Deadline task that inherits
 * from Task.
 */
public class Deadline extends Task {
    protected String description;
    protected boolean isDone;
    protected DateFormat date;

    /**
     * Constructor for this Deadline task.
     *
     * @param description The Deadline task.
     * @param date The date for the Deadline task.
     */
    public Deadline(String description, String date) {
        this.description = description;
        this.date = new DateFormat(date);
    }

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
     * Returns a string representation of whether
     * the Deadline task is done or undone.
     *
     * @return Returns a string representation of whether
     * the Deadline task is done or undone.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the string representation of the Deadline task.
     *
     * @return Returns the string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " +
                this.description + " (by: " + this.date.toString() + ")";
    }
}

package task;

import dateformat.DateFormat;

/**
 * Represents an Event task that inherits
 * from Task.
 */
public class Event extends Task {
    protected String description;
    protected boolean isDone;
    protected DateFormat date;

    /**
     * Constructor for this Event task.
     *
     * @param description The Event task.
     * @param date The date for the Event task.
     */
    public Event(String description, String date) {
        this.description = description;
        this.date = new DateFormat(date);
    }

    /**
     * Changes the boolean isDone depending on whether the user
     * wants to mark or unmark the Event task.
     *
     * @param bool The boolean for whether the Event task is marked
     *             or unmarked.
     */
    public void isMark(boolean bool) {
        this.isDone = bool;
    }

    /**
     * Returns a string representation of whether
     * the Event task is done or undone.
     *
     * @return Returns a string representation of whether
     * the Event task is done or undone.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the string representation of the Event task.
     *
     * @return Returns the string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " +
                this.description + " (at: " + this.date.toString() + ")";
    }
}

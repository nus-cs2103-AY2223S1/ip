package jude.task;

/**
 * An {@code Event} object is a Task which has a start time and an end time.
 */
public class Event extends Task {
    private final String when;

    /**
     * Creates a new {@code Event} object with a given description, whether it has been done, the
     * start time and the end time.
     *
     * @param description The description of the event.
     * @param isDone Whether the event is marked as done.
     * @param when The time of the event.
     */
    public Event(String description, boolean isDone, String when) {
        super(description, isDone);
        this.when = when;
    }

    /**
     * Returns when the {@code Event} is scheduled to take place.
     *
     * @return the time range which the {@code Event} is scheduled to take table.
     */
    public String getStartTime() {
        return when;
    }


    /**
     * Returns the task type code for an {@code Event} object, i.e. "E".
     *
     * @return "E".
     */
    @Override
    public String getTaskTypeCode() {
        return "E";
    }

    /**
     * Returns the String representation of the {@code Event} object, i.e.
     * a string in the format "[task type code][get status icon] description (at: time of event)".
     *
     * @return String representation of the {@code Event} object.
     */
    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), when);
    }

    /**
     * Returns a String representation of the {@code Event} object which in a format convenient to
     * save and load files.
     *
     * The string returned is in the following format (with newlines in between components and in
     * the end):
     * Task Type Code, i.e. "E" for {@code Event} objects
     * Description
     * 1 if the task is done, and 0 otherwise
     * The time which the {@code Event} takes place
     *
     * @return The String representation of the {@code Event} object.
     */
    @Override
    public String toFileSaveString() {
        return String.format("%s%s\n", super.toFileSaveString(), when);
    }

    /**
     * Returns false because {@code Event} objects do not support reminders.
     *
     * @param seconds Number of seconds time notice required.
     * @return False
     */
    @Override
    public boolean needsReminder(long seconds) {
        return false;
    }
}

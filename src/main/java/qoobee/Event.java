package qoobee;

/**
 * Represents an event with a description and date.
 */
public class Event extends Task {

    protected String at;
    protected final String TASK_TYPE = "E";

    /**
     * Creates an Event given a description and date.
     * @param description The details of the Event.
     * @param at The date of the Event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the date of this Event task.
     * @return The String of the date of Event.
     */
    public String getAt() {
        return this.at;
    }

    /**
     * Returns the String representation of this Event task.
     * @return The String representation of this Event task.
     */
    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString() + " (at:" + at + ")";
    }

    /**
     * Returns the String representation of this event task to be stored in storage.
     * @return The String representation of this event task.
     */
    @Override
    public String storageToString() {
        String status;
        if (getStatusIcon() == "X") {
            status = "1 | ";
        } else {
            status = "0 | ";
        }
        return TASK_TYPE + " | " + status + " | " + getDescription() + " | " + getAt()
                + " | " + getPriorityLevel() + "\n";
    }

}

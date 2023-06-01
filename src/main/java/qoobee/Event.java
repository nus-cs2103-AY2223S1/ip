package qoobee;

import java.time.LocalDateTime;

/**
 * Represents an event with a description and date.
 */
public class Event extends Task {

    protected LocalDateTime dateTime;
    protected final String TASK_TYPE = "E";

    /**
     * Creates an Event given a description and date.
     * @param description The details of the Event.
     * @param dateTime The date of the Event (in string).
     */
    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = DateTimeParser.getDateTime(dateTime);
    }

    /**
     * Returns the String representation of this event task.
     * @return The String representation of this event task.
     */
    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString()
                + " (by: " + DateTimeParser.getDateTimeString(dateTime) + ")";
    }

    /**
     * Returns the String representation of this event task to be stored in storage.
     * @return The String representation of this event task.
     */
    @Override
    public String storageToString() {
        String status;
        if (getStatusIcon().equals("X")) {
            status = "1 | ";
        } else {
            status = "0 | ";
        }
        return TASK_TYPE + " | " + status + getDescription()
                + " | " + DateTimeParser.getDateTimeStorage(dateTime) + " | " + getPriorityLevel()
                + "\n";
    }

}

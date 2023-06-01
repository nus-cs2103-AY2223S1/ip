package qoobee;

import java.time.LocalDateTime;

/**
 * Represents a deadline with description, date and time.
 */
public class Deadline extends Task {

    protected LocalDateTime dateTime;
    protected final String TASK_TYPE = "D";

    /**
     * Creates a deadline given a description and date.
     * @param description The details of the deadline.
     * @param dateTime The date (in string) to be completed by.
     */
    public Deadline(String description, String dateTime) {
        super(description);
        this.dateTime = DateTimeParser.getDateTime(dateTime);
    }

    /**
     * Creates a deadline given a description and dateTime object.
     * @param description The details of the deadline.
     * @param dateTime The dateTime to be completed by.
     */
    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Returns the String representation of this deadline task.
     * @return The String representation of this deadline task.
     */
    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString()
                + " (by: " + DateTimeParser.getDateTimeString(dateTime) + ")";
    }

    /**
     * Returns the String representation of this deadline task to be stored in storage.
     * @return The String representation of this deadline task.
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

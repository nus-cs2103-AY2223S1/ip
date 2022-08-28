package wanya.task;

import wanya.parser.DateTimeParser;

import java.time.DateTimeException;
import java.time.LocalDateTime;

/**
 * Represents the event task.
 */
public class Event extends Task {
    private LocalDateTime date;
    private final String TASK_TYPE = "E";

    /**
     * Creates an Event object when given task name and date.
     * 
     * @param taskName name of the event task.
     * @param date due date of the event task.
     * @throws DateTimeException if invalid date format is given.
     */
    public Event(String taskName, String date) throws DateTimeException {
        super(taskName);
        this.date = DateTimeParser.getDateTime(date);
    }

    /**
     * Creates an Event object when given task name, completeness and due date.
     *
     * @param taskName name of the event task.
     * @param hasCompleted whether the task has been completed.
     * @param date due date of the event task. 
     * @throws DateTimeException if invalid date format is given.
     */
    public Event(String taskName, boolean hasCompleted, String date) throws DateTimeException {
        super(taskName, hasCompleted);
        this.date = DateTimeParser.getDateTime(date);
    }

    /**
     * Returns the String representation of the event task.
     *
     * @return String representation of the event task.
     */
    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString() + "(at: " + DateTimeParser.getDateTimeString(date) + ")";
    }

    /**
     * Encodes an event object to a String representation for storage.
     *
     * @return String representation of the event task in storage.
     */
    @Override
    public String toStorageString() {
        return TASK_TYPE + "|" + super.toStorageString()
                + "|" + DateTimeParser.getDateTimeStorage(date);
    }
}

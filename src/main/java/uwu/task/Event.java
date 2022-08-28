package uwu.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import uwu.exception.UwuException;

/**
 * Represents a task of type Event.
 */
public class Event extends Task {
    /** The date and time of when the event takes place. */
    protected LocalDateTime at;

    /**
     * Constructor for an Event object.
     *
     * @param description The description of the Event task.
     * @param at The String value of date and time of when the task is due.
     * @throws UwuException If DateTime is invalid;
     *                      If DateTime is parsed in incorrect format.
     */
    public Event(String description, String at) throws UwuException {
        super(description);
        this.at = new UwuDateTime(at).getDateTime();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString() + " (at: " + at.format(formatter) + ")";
    }

    @Override
    public String toStorageString() {
        String isDoneIndicator = super.isDone ? "1" : "0";
        String atString = at.toString().replaceAll("T", " ");

        return "E," + isDoneIndicator + "," + description.trim() + "," + atString;
    }
}

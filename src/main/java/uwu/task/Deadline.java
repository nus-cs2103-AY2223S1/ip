package uwu.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import uwu.exception.UwuException;

/**
 * Represents a task of type Deadline.
 */
public class Deadline extends Task {
    /** The date and time of when the task is due. */
    protected LocalDateTime by;

    /**
     * Constructor for a Deadline object.
     *
     * @param description The description of the Deadline task.
     * @param by The String value of date and time of when the task is due.
     * @throws UwuException If DateTime is invalid;
     *                      If DateTime is parsed in incorrect format.
     */
    public Deadline(String description, String by) throws UwuException {
        super(description);
        this.by = new UwuDateTime(by).getDateTime();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    @Override
    public String toStorageString() {
        String isDoneIndicator = super.isDone ? "1" : "0";
        String byString = by.toString().replaceAll("T", " ");
        return "D," + isDoneIndicator + "," + description.trim() + "," + byString;
    }
}
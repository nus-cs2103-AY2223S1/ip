package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exception.DukeInvalidTimeException;

/**
 * Deadline class that stores the Description and State of Deadline.
 *
 * CS2103T IP
 * AY22/23 Semester 1
 * @author Tan Jia Rong
 */
public class Deadline extends Task {
    /** Stores the due date of the Deadline. */
    protected String by;

    /** Stores the due date of theDeadline in dateTime format. */
    protected String dateTime;

    /**
     * Constructor for Deadline.
     *
     * @param description Description of the Deadline task.
     * @param by The due date of the Deadline.
     */
    public Deadline(String description, String by) throws DukeInvalidTimeException {
        super(description, "D");
        this.by = by;
        this.dateTime = this.getDateTime();
    }

    /**
     * Constructor for Deadline.
     *
     * @param description Description of the Deadline.
     * @param done Completeness of Deadline.
     * @param by The due date of the Deadline.
     */
    public Deadline(String description, String done, String by) throws DukeInvalidTimeException {
        super(description, done, "E");
        this.by = by;
        this.dateTime = this.getDateTime();
    }

    /**
     * Returns the Date and Time of Task
     *
     * @return Date and Time of Task
     * @throws DukeInvalidTimeException Exception when input time is not of valid Format
     */
    public String getDateTime() throws DukeInvalidTimeException {
        String dateStr;
        String timeStr;
        String[] input = this.by.split(" ");

        // get Date
        LocalDate date = LocalDate.parse(input[0]);
        dateStr = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));

        // get Time
        if (input[1].length() != 4) {
            throw new DukeInvalidTimeException();
        }

        int time = Integer.parseInt(input[1]);
        int hour = time / 100;
        int hourHand = hour > 12 ? hour % 12 : hour;
        int min = time % 100;

        if (hour == 0) {
            return dateStr + " " + "12am";
        } else if (min != 0) {
            String hourStr = String.valueOf(hourHand);
            String minStr = min < 10 ? "0" + min : String.valueOf(min);
            String amPm = hour < 12 ? "am" : "pm";
            timeStr = hourStr + ":" + minStr + amPm;
        } else {
            String hourStr = String.valueOf(hourHand);
            String amPm = hour < 12 ? "am" : "pm";
            timeStr = hourStr + amPm;
        }

        return dateStr + " " + timeStr;
    }

    /**
     * Gets due date of Deadline.
     *
     * @return Due date of Deadline.
     */
    public String getDate() {
        return this.by;
    }

    /**
     * Returns the string representation of a Deadline.
     *
     * @return String representation of a Deadline.
     */
    @Override
    public String toString() {
        return "[" + this.getType() + "]" + super.toString() + " (by: " + this.dateTime + ")";
    }
}

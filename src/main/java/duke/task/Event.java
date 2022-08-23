package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeInvalidTimeException;

/**
 * Event class that stores the Description and State of Event.
 *
 * CS2103T IP
 * AY22/23 Semester 1
 * @author Tan Jia Rong
 */
public class Event extends Task {
    /** Stores the timing of the event */
    protected String at;
    protected String dateTime;

    /**
     * Constructor for Event.
     *
     * @param description Description of the Event.
     * @param at The timing of the Event.
     */
    public Event(String description, String at) throws DukeInvalidTimeException {
        super(description, "E");
        this.at = at;
        this.dateTime = this.getDateTime();
    }

    /**
     * Constructor for Event.
     *
     * @param description Description of the Event.
     * @param done Completeness of Event.
     * @param at The timing of the Event.
     */
    public Event(String description, String done, String at) throws DukeInvalidTimeException {
        super(description, done, "E");
        this.at = at;
        this.dateTime = this.getDateTime();
    }

    /**
     * Returns the Date and Time of Duke.Task.Task
     *
     * @return Date and Time of Duke.Task.Task
     * @throws DukeInvalidTimeException Exception when input time is not of valid Format
     */
    public String getDateTime() throws DukeInvalidTimeException {
        String dateStr;
        String timeStr;
        String[] input = this.at.split(" ");

        // get Date
        try {
            LocalDate date = LocalDate.parse(input[0]);
            dateStr = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            throw new DukeInvalidTimeException();
        }
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
     * Gets timing of Event.
     *
     * @return Timing of Event.
     */
    public String getDate() {
        return this.at;
    }

    /**
     * Returns the string representation of the Event.
     *
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        return "[" + this.getType() + "]" + super.toString() + " (at: " + this.dateTime + ")";
    }
}

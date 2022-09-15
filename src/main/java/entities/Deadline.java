package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exceptions.DukeException;

/**
 * A subclass of the task class.
 */
public class Deadline extends Task {
    /**
     * The (String) due date or time of the Deadline instance.
     */
    protected String by;

    /**
     * The actual date and time of the Deadline instance.
     */
    protected LocalDateTime dateTime;

    /**
     * Constructor for a Deadline instance.
     * @param description The description of the Deadline instance.
     * @param by The due date or time of the Deadline instance.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = by;
        String date = by.split(" ")[0].trim();
        String time = by.split(" ")[1].trim();
        try {
            this.dateTime = LocalDateTime.parse(date + "T" + time.substring(0, 2) + ":" + time.substring(2));
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date-time entered.");
        }

    }

    /**
     * Returns a reader-friendly version of the date/time input
     * by the user.
     * @return A String representing the input date/time in
     *         MMM d yyyy XX.xx am/pm format.
     */
    private String getFormattedDateTime() {
        String s = "";
        s = s + this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy "));
        String time = this.dateTime.toLocalTime().toString();
        int hours = Integer.parseInt(time.substring(0, 2));
        String meridiem = hours / 12 == 0 ? "am" : "pm";
        hours = hours % 12;
        s = s + hours + "." + time.substring(3) + meridiem;
        return s;
    }

    public boolean hasDeadlineAfter(LocalDateTime otherDateTime) {
        return this.dateTime.isAfter(otherDateTime);
    }

    /**
     * Overrides the toString method in the parent class.
     * @return A string representing the current deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getFormattedDateTime() + ")";
    }

    /**
     * Returns the String date/time of the current Deadline instance.
     * @return The same String entered by the user after the '/by' specifier.
     */
    public String getRemarks() {
        return this.by;
    }

    /**
     * Sets the date time for the deadline instance.
     * @param dateTime The date and time value to set.
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}

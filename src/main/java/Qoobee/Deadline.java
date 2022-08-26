package Qoobee;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline with description, date and time.
 */
public class Deadline extends Task {

    protected LocalDateTime dateTime;

    /**
     * Creates a deadline given a description and date.
     * @param description The details of the deadline.
     * @param by The date (in string) to be completed by.
     */
    public Deadline(String description, String by) {
        super(description);
        String[] curr = by.split(" ", 2);
        String[] date = curr[0].split("/", 3);
        String hour = curr[1].substring(0, 2);
        String minute = curr[1].substring(2, 4);
        this.dateTime = LocalDateTime.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]),
                Integer.parseInt(date[0]), Integer.parseInt(hour), Integer.parseInt(minute));
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
     * Returns the date and time in String representation, after formatting.
     * @return The String representation of a date and time.
     */
    public String getDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy HH:mm");
        return this.dateTime.format(formatter);
    }

    /**
     * Returns the String representation of this deadline task.
     * @return The String representation of this deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + getDateTime() + ")";
    }
}
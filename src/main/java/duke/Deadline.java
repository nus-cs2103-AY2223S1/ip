package main.java.duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a type of task called "Deadline", with a specified end date and time
 *
 * @author eugeneleong
 * @version 1.0
 */

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String action, LocalDateTime by) {
        super(action);
        this.by = by;
    }

    /**
     * Gives the date of the deadline as output
     * @return date (in dd-MMM-yyyy)
     */
    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        return by.format(formatter);
    }

    /**
     * Gives the time of the deadline as output
     * @return time (in HH:mm)
     */
    public String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return by.format(formatter);
    }

    /**
     * Returns the description of a Deadline task
     * @return [type = Deadline][marked?] description and date/time
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDate() + " " + this.getTime() + ")";
    }
}

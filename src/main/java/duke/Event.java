package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a type of task called "Event", with a specified end date and time
 *
 * @author eugeneleong
 * @version 1.0
 */

public class Event extends Task {

    protected LocalDateTime at;

    /**
     * Constructor for a type of task called Deadline
     * @param action description of Event task
     * @param at when to commence with the Event task
     */
    public Event(String action, LocalDateTime at) {
        super(action);
        this.at = at;
    }

    /**
     * Gives the date of the event as output
     * @return date (in dd-MMM-yyyy)
     */
    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        return at.format(formatter);
    }

    /**
     * Gives the time of the event as output
     * @return time (in HH:mm)
     */
    public String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return at.format(formatter);
    }

    /**
     * Returns the description of an Event task
     * @return [type = Event][marked?] description and date/time
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (at: " + this.getDate() + " " + this.getTime() + ")";
    }
}

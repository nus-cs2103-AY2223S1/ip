package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event object class.
 */
public class Event extends Task {

    protected String at;
    protected LocalDate date;

    /**
     * Creates an event object with description and time at.
     *
     * @param description Details of the event.
     * @param at          Time the event occurs.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.date = LocalDate.parse(at);
    }

    /**
     * Represents an Event object.
     *
     * @return string format of Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

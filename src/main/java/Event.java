package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event encapsulates an event with a date/time period.
 *
 * @author Totsuka Tomofumi
 */
public class Event extends Task {
    /**
     * Event date/time period.
     */
    private LocalDate time;

    /**
     * Constructor for this event.
     * @param description Description of event
     * @param time Time period of event
     */
    public Event(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    /**
     * Returns a string representation of the event.
     * @return event status and its description
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + String.format(" (at: %s)", time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String toData() {
        return "E" + super.toData() + this.time;
    }
}

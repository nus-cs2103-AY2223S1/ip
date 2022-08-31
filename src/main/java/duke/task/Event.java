package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Event class that stores the Description and State of Event.
 *
 * CS2103T IP
 * AY22/23 Semester 1
 * @author Tan Jia Rong
 */
public class Event extends Task {
    /** Stores the timing of the Event */
    protected LocalDateTime at;

    /**
     * Constructor for Event.
     *
     * @param description Description of the Event.
     * @param at The timing of the Event.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructor for Event.
     *
     * @param description Description of the Event.
     * @param isDone Completeness of Event.
     * @param at The timing of the Event.
     */
    public Event(String description, boolean isDone, LocalDateTime at) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Returns the Date and Time of Event.
     *
     * @return Date and Time of Event.
     */
    public String printDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY MMM dd hh:mma");
        return at.format(formatter);
    }

    /**
     * Stringify event for storage.
     *
     * @return a string representing the event.
     */
    @Override
    public String stringify() {
        return String.format("%s | %s | %s", "E", super.stringify(), at);
    }

    /**
     * Returns the string representation of the Event.
     *
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + printDateTime() + ")";
    }
}

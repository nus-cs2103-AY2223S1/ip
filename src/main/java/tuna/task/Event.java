package tuna.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task. An Event object contains the start time of the event.
 */
public class Event extends Task {

    /** Start time of the event */
    protected LocalDateTime at;

    /**
     * Creates an event with a start time.
     *
     * @param description the description of the event.
     * @param at the start time of the event.
     */
    public Event(String description, String at) {
        super(description, "E");
        this.at = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Returns the start time of the event.
     *
     * @return start time of the event.
     */
    public String getAt() {
        return this.at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * String representation of the Event object.
     *
     * @return String representation of the event.
     */
    @Override
    public String toString() {
        return "[" + super.getTaskType() + "]" + super.toString() + " (at: " + parseDateTime(at) + ")";
    }
}

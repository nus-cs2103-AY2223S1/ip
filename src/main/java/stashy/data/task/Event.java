package stashy.data.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * One of the task types, specifically those with
 * specified timing of when it's happening.
 */
public class Event extends Task {

    /**
     * The time of the event as a LocalDateTime object.
     */
    protected LocalDateTime at;

    /**
     * Constructor method.
     *
     * @param description The event description
     * @param at The time of the event
     * @param isDone Whether the task is done or not
     */
    public Event(String description, LocalDateTime at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Constructor method.
     *
     * @param description The event description
     * @param at The time of the event
     */
    public Event(String description, LocalDateTime at) {
        this(description, at, false);
    }

    /**
     * Converts the Event object into a string.
     *
     * @return The stringtified version with the event time in "MMM dd yyyy HHmm" format
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }
}
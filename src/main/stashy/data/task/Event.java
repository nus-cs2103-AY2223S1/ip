package main.stashy.data.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * One of the task types, specifically those with
 * specified timing of when it's happening.
 */
public class Event extends Task {

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

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }
}
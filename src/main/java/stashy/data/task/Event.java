package stashy.data.task;

import java.time.LocalDateTime;

/**
 * One of the task types, specifically those with
 * specified timing of when it's happening.
 */
public class Event extends Task {

    /**
     * The time of the event as a LocalDateTime object.
     */
    protected LocalDateTime atDateTime;

    /**
     * Constructor method.
     *
     * @param description The event description
     * @param atDateTime The time of the event
     * @param isDone Whether the task is done or not
     */
    public Event(String description, LocalDateTime atDateTime, boolean isDone) {
        super(description, isDone);
        this.atDateTime = atDateTime;
    }

    /**
     * Constructor method.
     *
     * @param description The event description
     * @param atDateTime The time of the event
     */
    public Event(String description, LocalDateTime atDateTime) {
        this(description, atDateTime, false);
    }

    /**
     * Converts the Event object into a string.
     *
     * @return The stringtified version with the event time in "MMM dd yyyy HHmm" format
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + atDateTime.format(OUTPUT_DATETIME_PATTERN) + ")";
    }
}

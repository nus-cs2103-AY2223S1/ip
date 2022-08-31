package duke.task;

import java.time.LocalDate;

/**
 * Represents a task-type with description, date, and completion status.
 *
 * @author Tan Jun Wei
 */
public class Event extends Task {
    /** The date of the event. */
    protected LocalDate localDate;

    /**
     * Constructs a new event task (with default completion status).
     *
     * @param description The description of the event.
     * @param date The date of the event.
     */
    public Event(String description, LocalDate date) {
        super(description);
        this.localDate = date;
    }

    /**
     * Constructs a new event task (with specified completion status).
     *
     * @param description The description of the event.
     * @param localDate The date of the event.
     * @param isDone The completion status of the event.
     */
    public Event(String description, LocalDate localDate, boolean isDone) {
        super(description, isDone);
        this.localDate = localDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String encode() {
        return "E" + Task.ENCODING_SEPARATOR + localDate + Task.ENCODING_SEPARATOR + super.encode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + localDate + ")";
    }
}
package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is a type of Task that has a date to it.
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Constructor for Event.
     *
     * @param description Description of the Event.
     * @param at The date of the Event.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructor for Event in save file.
     *
     * @param description Description of the Event.
     * @param done If the Event task is done or not done.
     */
    public Event(String description, LocalDate at, boolean done) {
        super(description, done);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Getter to access at field.
     *
     * @return The due time
     */
    public LocalDate getAt() {
        return this.at;
    }
}
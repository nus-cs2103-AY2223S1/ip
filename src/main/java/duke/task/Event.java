package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event Task.
 * Inherits from Task.
 */
public class Event extends Task {

    private LocalDate at;

    /**
     * Creates the Event.
     *
     * @param description The Event at hand.
     * @param at When the Event is at.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Creates the Event.
     *
     * @param description The Event at hand.
     * @param at When the Event is at.
     * @param isDone Whether is Event is done already.
     */
    public Event(String description, LocalDate at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Returns the at variable.
     *
     * @return The at variable.
     */
    public LocalDate getAt() {
        return this.at;
    }

    /**
     * Returns the String representation of Event.
     *
     * @return The String representation of Event showing the status and description.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}

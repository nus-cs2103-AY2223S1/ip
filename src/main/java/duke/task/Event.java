package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is a Task that represents an event.
 *
 * @author John Russell Himawan
 * @version CS2103T AY22/23 Sem 1
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Constructor for Event.
     *
     * @param description String representation of description.
     * @param at Date when the event is held.
     */
    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    /**
     * Returns the date the event is held.
     *
     * @return LocalDate on when the event is held.
     */
    public LocalDate getAt() {
        return this.at;
    }

    /**
     * Returns String representation of the event.
     *
     * @return String representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

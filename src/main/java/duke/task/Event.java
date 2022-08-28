package duke.task;

import java.time.LocalDate;

/**
 * Represents an event occurring on a specific date.
 */
public class Event extends Task {
    private final LocalDate at;

    /**
     * Constructor for an Event.
     * @param description Description for the event.
     * @param at Date of the event.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toStorage() {
        return "E|" + super.toStorage() + "|" + this.at.format(Event.INPUT_DATE_FORMAT);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at.format(Event.OUTPUT_DATE_FORMAT) + ")";
    }
}

package duke.task;

import java.time.LocalDate;

/**
 * Represents an event occurring on a specific date.
 */
public class Event extends Task {
    public static final String STORAGE_CHAR = "E";
    private final LocalDate at;

    /**
     * Creates a task happening at a specified date.
     *
     * @param description Description for the event.
     * @param at Date of the event.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toStorage() {
        return STORAGE_CHAR + STORAGE_SEPARATOR + super.toStorage() + STORAGE_SEPARATOR
                + at.format(Event.INPUT_DATE_FORMAT);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(Event.OUTPUT_DATE_FORMAT) + ")";
    }
}

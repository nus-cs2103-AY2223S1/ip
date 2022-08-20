package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is a Task that happens at a specific date/time.
 *
 * @author Jet Lee
 * @version CS2103T AY22/23 Sem 1
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Constructor for Event.
     *
     * @param description Description of the Event.
     * @param at The date/time when the Event happens.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructor for Event with information on whether it is completed.
     *
     * @param description Description of the Event.
     * @param isDone Whether the Event is completed.
     * @param at The date/time when the Event happens.
     */
    public Event(String description, boolean isDone, LocalDate at) {
        super(description);
        this.at = at;
        this.isDone = isDone;
    }

    /**
     * Returns the String representation of the Event.
     *
     * @return String detailing the Event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)",
                super.toString(), at.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * Converts the Event to data to be saved.
     *
     * @return Data representing the Event.
     */
    @Override
    public String saveTask() {
        return String.format("E | %s | %s", super.saveTask(), at);
    }
}

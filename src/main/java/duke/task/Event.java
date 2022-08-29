package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class representing an Event task.
 */
public class Event extends Task {
    /**
     * Task of type "E".
     */
    protected final String type = "E";
    /**
     * LocalDate of when event is occurring at.
     */
    protected LocalDate at;

    /**
     * Constructor for a new Event.
     *
     * @param description Description of event.
     * @param at          LocalDate of at.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Gets LocalDate of when event is occurring at.
     *
     * @return LocalDate at
     */
    public LocalDate getAt() {
        return this.at;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + super.toString()
                + "(at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

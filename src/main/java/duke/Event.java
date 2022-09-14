package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An Event class which is a subclass of Task.
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Constructor for Event class.
     *
     * @param description Description of the event.
     * @param at Date of the event in String format.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toSavedString() {
        return "E | " + (this.isDone ? "1 | " : "0 | ") + this.description + " | " + this.at;
    }
}

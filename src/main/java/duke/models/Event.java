package duke.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event.
 *
 * @author Zhu Yuanxi
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Constructs an event object.
     *
     * @param description The description of the event.
     * @param at The date of the event.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Formats the string representation of the event object for save.
     *
     * @return The string representation of a event object for save.
     */
    public Event(String description, LocalDate at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    public String formatForSave() {
        return "E | " + super.formatForSave() + " | " + at;
    }

    /**
     * Formats the string representation of the event object for display.
     *
     * @return The string representation of a event object for display.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")\n";
    }
}

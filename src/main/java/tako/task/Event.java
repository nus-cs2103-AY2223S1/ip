package tako.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event.
 */
public class Event extends Task {
    protected LocalDateTime at;

    /**
     * Constructor for an event.
     *
     * @param description Description of event.
     * @param at Date and time event occurs at.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString() + " (at: " + at.format(formatter) + ")";
    }
}

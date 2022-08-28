package duke.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Represents a task that occurs at a specific time.
 */
public class Event extends Task {

    private LocalDateTime at;

    /**
     * Initialises an Event with its description and date.
     * @param description Description of the event.
     * @param at DateTime that event occurs.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Gets String representation of this event.
     * @return String representation of this event.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString() + " (at: " + at.format(formatter) + ")";
    }

}

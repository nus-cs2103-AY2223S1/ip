package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a event task.
 */
public class Event extends Task {

    protected LocalDateTime at;

    /**
     * Class constructor.
     *
     * @param description Description of event task.
     * @param at Date and time of the event task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = LocalDateTime.parse(at);
    }

    @Override
    public String saveString() {
        return "E | " + (this.isDone ? "1 | " : "0 | ") + this.description + " | " + this.at;
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (at: " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm:ss")) + ")";
    }
}

package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents an event task that can be stored by Duke.
 * Event tasks must be specified with the date of the event.
 */
public class Event extends Task {

    protected LocalDate at;

    /**
     * Constructor for an event task.
     *
     * @param description description of the event.
     * @param at date of the event.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Converts the event to a String
     *
     * @return the string representation of an event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM-d-yyyy")) + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            Event event = (Event) obj;
            return Objects.equals(this.description, event.description)
                    && Objects.equals(this.at, event.at);
        }
    }

    /**
     * Converts the event task to its string representation.
     *
     * @return String representation of the event.
     */
    @Override
    public String getSaveString() {
        return "E | " + (isDone ? "1 | " : "0 | ") + this.description + " | " + this.at;
    }
}

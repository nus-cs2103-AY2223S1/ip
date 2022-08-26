package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event which is one type of task.
 */
public class Event extends Task {
    private LocalDate at;

    /**
     * Constructs a new Event instance with a description and a date.
     *
     * @param description Description of the event.
     * @param at Date of the event.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}

package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event which is one type of task.
 */
public class Event extends Task {
    private LocalDate eventDate;

    /**
     * Constructs a new Event instance with a description and a date.
     *
     * @param description Description of the event.
     * @param eventDate Date of the event.
     */
    public Event(String description, LocalDate eventDate) {
        super(description);
        this.eventDate = eventDate;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + eventDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}

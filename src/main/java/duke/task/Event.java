package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event type Task.
 */
public class Event extends Task {
    private LocalDate eventTime;

    /**
     * Creates an Event that is not done.
     *
     * @param description Description of this Event.
     * @param deadline The actual date of this Event.
     */
    public Event(String description, LocalDate eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    /**
     * Creates an Event with a specified done status.
     *
     * @param isDone The done status of the Event.
     * @param description Description of this Event.
     * @param deadline The actual date for this Event.
     */
    public Event(boolean isDone, String description, LocalDate eventTime) {
        super(isDone, description);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + eventTime.format(DateTimeFormatter.ofPattern("dd MMM yy")) + ")";
    }

    /**
     * Returns a String representation of the Event that is suitable for storing in a text file.
     *
     * @return A string representation of the Event for storing in a text file.
     */
    public String toDbString() {
        return "E" + " | " + super.toDbString() + " | "
                + eventTime.format(DateTimeFormatter.ofPattern("dd MMM yy"));
    }
}

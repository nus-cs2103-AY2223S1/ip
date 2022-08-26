package duke.task;

import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is a user set task happening at a certain time.
 *
 * @author totsukatomofumi
 */
public class Event extends Task {
    /** Time the event is at. */
    private LocalDate time;

    /**
     * Constructs an event.
     *
     * @param description the description of the event.
     * @param time the time the event is at.
     */
    public Event(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    /**
     * Returns a string representation of the event.
     *
     * @return a string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + String.format(" (at: %s)", time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * Returns a string representation of the event that conforms to how storage
     * parses from the task history file.
     *
     * @return the parsable string representation of the event.
     */
    @Override
    public String toData() {
        return "E" + super.toData() + this.time.toString();
    }
}

package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a task that is happening at a certain time.
 *
 * @author Marcus Low
 */
public class Event extends Task {
    private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
    private final LocalDateTime time;

    /**
     * Constructs an Event task.
     *
     * @param description Description of the task.
     * @param time Time when the event happens.
     */
    public Event(String description, LocalDateTime time) {
        super(description, false);
        this.time = time;
    }

    /**
     * Constructs an Event task.
     *
     * @param description Description of the task.
     * @param isDone Whether the task is done or not.
     * @param time Time when the event happens.
     */
    public Event(String description, boolean isDone, LocalDateTime time) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[E][X] " + this.description + " (at: " + this.time.format(DF) + ")";
        } else {
            return "[E][ ] " + this.description + " (at: " + this.time.format(DF) + ")";
        }
    }

    @Override
    public String toStringText() {
        return "E | " + this.description + " | " + this.isDone + " | " + this.time.format(DF);
    }
}

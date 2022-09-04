package tuna.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task.
 */
public class Event extends TimeBasedTask {
    /**
     * Creates an event with a start time.
     *
     * @param description the description of the event.
     * @param at the start time of the event.
     */
    public Event(String description, String at) {
        super(description, "E", LocalDateTime.parse(at, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    /**
     * String representation of the Event object.
     *
     * @return String representation of the event.
     */
    @Override
    public String toString() {
        return "[" + super.getTaskType() + "]" + super.toString() + " (at: " + parseDateTime(this.getDateTime()) + ")";
    }
}

package cwq.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class encapsulates Duke events
 */
public class Event extends Task {
    private final LocalDateTime eventTime;

    /**
     * Constructor for Event class
     * @param taskDescription the content of the task
     * @param eventTime the time for the event
     */
    public Event(String taskDescription, String eventTime) {
        super(taskDescription);
        this.eventTime = LocalDateTime.parse(eventTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public LocalDateTime getTime() {
        return eventTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " " + "(at: " + eventTime.format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ")";
    }
}

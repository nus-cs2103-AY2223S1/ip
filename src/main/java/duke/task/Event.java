package duke.task;

import java.time.LocalDateTime;

/**
 * Represents an event in Duke.
 */
public class Event extends Task {
    protected LocalDateTime eventTiming;

    /**
     * Creates a new instance of event.
     *
     * @param description The description of an event.
     * @param eventTiming The datetime of the event.
     */
    public Event(String description, LocalDateTime eventTiming) {
        super(description);
        assert !description.isEmpty() : "Description of an event should not be empty";

        this.eventTiming = eventTiming;
        this.taskType = TaskType.EVENT;
    }

    /**
     * Encodes the event into a decodable and readable string representation for storage.
     *
     * @return The encoded string representation of an event for storage.
     */
    @Override
    public String encode() {
        return super.encode() + " | " + this.eventTiming;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + eventTiming.format(Task.DATE_TIME_DISPLAY_FORMATTER) + ")";
    }
}

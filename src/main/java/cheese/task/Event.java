package cheese.task;

import java.time.LocalDateTime;
import cheese.utils.DateTimeUtils;

/**
 * Represents an event with description, complete/incomplete status, and time interval.
 */
public class Event extends Task {
    /** Time interval of event. */
    private LocalDateTime timeInterval;

    /**
     * Constructs an instance of <code>Event</code>.
     * 
     * @param description Description of event.
     * @param timeInterval Time interval of event.
     */
    public Event(String description, String timeInterval) {
        super(description);
        this.timeInterval = DateTimeUtils.parseString(timeInterval);
    }

    /**
     * Constructs an instance of <code>Event</code>.
     * 
     * @param isDone Whether event is complete or incomplete.
     * @param description Description of event.
     * @param timeInterval Time interval of event.
     */
    public Event(boolean isDone, String description, String timeInterval) {
        super(isDone, description);
        this.timeInterval = DateTimeUtils.parseString(timeInterval);
    }

    /**
     * Returns string representation of event to save in file (eg. event // T // Concert night //
     * 2022-12-07 18:00).
     * 
     * @return String representation of event to save in file.
     */
    @Override
    public String toFileString() {
        String formattedTimeInterval = DateTimeUtils.parseLocalDateTimeToInput(timeInterval);
        return "event // " + super.toFileString() + " // " + formattedTimeInterval;
    }

    /**
     * Returns string representation of event.
     * 
     * @return string representation of event.
     */
    @Override
    public String toString() {
        String formattedTimeInterval = DateTimeUtils.parseLocalDateTimeToOutput(timeInterval);
        return "[E]" + super.toString() + " (at: " + formattedTimeInterval + ")";
    }
}

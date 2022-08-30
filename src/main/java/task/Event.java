package task;

import java.time.LocalDateTime;

/**
 * Represents an event, a type of task.
 */
public class Event extends Task {
    private final LocalDateTime timing;

    /**
     * Constructs an event with some description and datetime for the event's start time.
     *
     * @param description The specified description.
     * @param isDone      The boolean indicating whether the task is done.
     * @param timing      The specified datetime string for the start time.
     */
    public Event(String description, boolean isDone, LocalDateTime timing) {
        super(description, isDone);
        this.timing = timing;
        this.taskType = TaskType.E;
    }

    /**
     * Retrieves formatted value of the event's timing.
     *
     * @return The formatted datetime string.
     */
    public String getTiming() {
        return Task.DATE_TIME_DISPLAY_FORMATTER.format(this.timing);
    }

    /**
     * Event has an additional datetime field for timing.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public String encode(String delimiter) {
        return super.encode(delimiter) + delimiter + this.timing.format(Task.DATE_TIME_PARSER);
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + this.getTiming() + ")";
    }
}

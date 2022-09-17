package duke.task;

import java.time.LocalDateTime;

/**
 * A Event object that is a type of Task, but contains the date and time on top of the task description.
 */
public class Event extends Task {

    /** Datetime of the event */
    protected LocalDateTime duration;

    /**
     * Creates an Event object.
     *
     * @param description the description of the event
     * @param duration the datetime of the event
     */
    public Event(String description, LocalDateTime duration) {
        super(description);
        this.duration = duration;
    }

    /**
     * Returns the LocalDateTime of this Event instance.
     *
     * @return the datetime information of this {@code Event}
     */
    public LocalDateTime getDuration() {
        return duration;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + duration.format(DATE_TIME_FORMATTER) + ")";
    }
}

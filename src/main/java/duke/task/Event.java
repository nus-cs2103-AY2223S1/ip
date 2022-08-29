package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is a Task that occurs at a specified date.
 *
 * @author Eugene Tan
 */
public class Event extends Task {
    protected LocalDate eventTime;

    /**
     * Constructor for Event.
     *
     * @param description The event description.
     * @param eventTime The date of event.
     */
    public Event(String description, LocalDate eventTime) {
        this.description = description;
        this.isDone = false;
        this.eventTime = eventTime;
    }

    /**
     * Constructor for Event.
     *
     * @param description The event description.
     * @param isDone Whether the event has been completed.
     * @param eventTime The date of event.
     */
    public Event(String description, boolean isDone, LocalDate eventTime) {
        this.description = description;
        this.isDone = isDone;
        this.eventTime = eventTime;
    }

    /**
     * Converts the Event to a String format to be used for saving.
     *
     * @return String detailing the event.
     */
    @Override
    public String saveStringFormat() {
        return String.format("E | %d | %s | %s", this.isDone ? 1 : 0, this.description,
                this.eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * Returns String representation of Event.
     *
     * @return String representation of Event.
     */
    @Override
    public String toString() {
        return "[E] " + "[" + this.getStatusIcon() + "] " + this.description
                + " (at: " + this.eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

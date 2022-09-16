package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event, which is a Task with time.
 */
public class Event extends Task {
    private final LocalDate time;

    /**
     * Constructor of Event with description and time.
     *
     * @param description Description of the Event.
     * @param time Time of the Event.
     */
    public Event(String description, String time) {
        super(description);
        this.time = LocalDate.parse(time);
        type = 'E';
    }

    /**
     * Constructor of Event with description, boolean to set the Event as done or not done, and time.
     *
     * @param description Description of the Event.
     * @param isDone Boolean to set the Event as done or not done.
     * @param time Time of the Event.
     */
    public Event(String description, boolean isDone, String time) {
        this(description, time);
        this.isDone = isDone;
    }

    /**
     * Returns the String representation of the Event for UI.
     *
     * @return String representation of the Event for UI.
     */
    @Override
    public String toString() {
        return super.toString() + " (at: " + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns the String representation of the Event for Storage.
     *
     * @return String representation of the Event for Storage.
     */
    @Override
    public String toData() {
        return super.toData() + ", " + time;
    }
}

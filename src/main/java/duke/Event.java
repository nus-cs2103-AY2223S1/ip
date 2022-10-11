package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task of the type event, with a specified time of occurence.
 */
public class Event extends Task {

    private String time;

    /**
     * Constructs an event object.
     *
     * @param title The given description of the deadline.
     * @param time The time of the deadline.
     * @param done Whether the task has been done.
     */
    public Event(String title, String time, boolean done) {
        super(title, "event", done);
        this.time = time;
    }

    @Override
    public String toString() {
        return (super.toString() + " (at: " + this.time + ")");
    }

    /**
     * Gets the time of the event.
     *
     * @return The time of the event as a string.
     */
    public String getTime() {
        return this.time;
    }
}

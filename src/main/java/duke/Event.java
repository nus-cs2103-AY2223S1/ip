package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task of the type event, with a specified time of occurence.
 */
public class Event extends Task {

    private LocalDate time;

    /**
     * Constructs an event object.
     *
     * @param title The given description of the deadline.
     * @param time The time of the deadline.
     * @param done Whether the task has been done.
     */
    public Event(String title, String time, boolean done) {
        super(title, "event", done);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.time = LocalDate.parse(time, formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return (super.toString() + " (at: " + this.time.format(formatter) + ")");
    }

    /**
     * Gets the time of the deadline in format of "yyyy-MM-dd".
     *
     * @return The time of the deadline as a string.
     */
    public String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return this.time.format(formatter);
    }
}

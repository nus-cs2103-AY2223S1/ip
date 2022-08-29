package justin.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a date and a time
 * at which it happens.
 * @author Justin Cheng.
 */
public class Event extends Task {
    protected LocalDate at;
    protected LocalTime time;

    /**
     * Constructor for the Event class
     * @param description The description of the event.
     * @param isDone The boolean value of whether the event is done.
     * @param at The date at which it happens in String.
     * @param time The time at which it happens in String.
     */
    public Event(String description, boolean isDone, String at, String time) {
        super(description, isDone);
        this.at = LocalDate.parse(at);
        this.time = LocalTime.parse(time);
    }

    /**
     * Returns a String to represent an Event task.
     * @return String representation of Event task.
     */
    @Override
    public String toString() {
        return "E | " + this.getStatusIcon() + " | " + this.getDescription() + " | " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + this.time.format(DateTimeFormatter.ofPattern("hhmma"));
    }
}

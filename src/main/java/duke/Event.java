package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task of type Event.
 */
public class Event extends Task {
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Initialises the Event task.
     *
     * @param description Describes the activity of the Event task.
     */
    public Event(String description) {
        super(description);
    }

    /**
     * Gets the string representation of the Event task.
     *
     * @return String representation of the Event task.
     */
    @Override
    public String toString() {
        String date = this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String time = this.time.toString();
        return "[E]" + super.toString() + " (at: " + date + " " + time + ")";
    }

    /**
     * Sets the Event task's date using string representation of date.
     *
     * @param date String representing the date to be set.
     */
    public void setDate(String date) {
        this.date = LocalDate.parse(date);
    }

    /**
     * Sets the Event task's date using date in the form of LocalDate.
     *
     * @param date LocalDate representing the date to be set.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Sets the Event task's time.
     *
     * @param time String representing the time to be set.
     */
    public void setTime(String time) {
        this.time = LocalTime.parse(time);
    }
}

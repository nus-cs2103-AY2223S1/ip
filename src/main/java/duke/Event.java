package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/** Represents a Task of type Event. */
public class Event extends Task {
    protected String at;
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Initialises the Event task.
     *
     * @param description Describes the activity of the Event task.
     * @param at Date and time of the Event task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Gets the string representation of the Event task.
     *
     * @return String representation of the Event task.
     */
    @Override
    public String toString() {
        if (this.at.contains("/") || at.contains("-")) {
            String date = this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            String time = this.time.toString();
            return "[E]" + super.toString() + " (at: " + date + " " + time + ")";
        } else {
            return "[E]" + super.toString() + " (at: " + at + ")";
        }
    }

    /**
     * Sets the event task's date.
     *
     * @param date String representing the date to be set.
     */
    public void setDate(String date) {
        this.date = LocalDate.parse(date);
    }

    /**
     * Sets the event task's time.
     *
     * @param time String representing the time to be set.
     */
    public void setTime(String time) {
        this.time = LocalTime.parse(time);
    }
}

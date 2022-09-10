package duke;

import java.time.format.DateTimeFormatter;

/** Represents a Task of type Event. */
public class Event extends Task{
    protected String at;

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
            String date = super.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            String time = super.time.toString();
            return "[E]" + super.toString() + " (at: " + date + " " + time + ")";
        } else {
            return "[E]" + super.toString() + " (at: " + at + ")";
        }
    }
}

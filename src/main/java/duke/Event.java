package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/** A class that creates the Event task. */
public class Event extends Task{
    protected String at;

    /**
     * A constructor for the Event task.
     *
     * @param description Describes the activity of the Event task.
     * @param at Date and time of the Event task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the description of the Event task.
     *
     * @return String that describes the activity, date and time of the Event task.
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

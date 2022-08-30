package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Event class represents a type of task that has a date and time of the task.
 */
public class Event extends Task {
    protected LocalDateTime at;

    /**
     * Constructor for Event.
     *
     * @param description the String that describes the task
     * @param at the LocalDateTime of the event
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the task in a specific format to save it in the text file.
     *
     * @return the string that represents the task in a specific format
     */
    @Override
    public String getDataFormat() {
        return String.format("E // %s // %s // %s", getStatusIcon(), description, at);
    }

    /**
     * Returns the string representation of the Task.
     *
     * @return a string that represents the task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + at.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)) + ")";
    }
}

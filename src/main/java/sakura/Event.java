package sakura;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event as a task in the list.
 */
public class Event extends Task {

    protected LocalDateTime at;

    /**
     * Constructor for event.
     *
     * @param description description of the event.
     * @param at the time of the event in datetime format.
     */
    public Event(String description, String at) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.at = LocalDateTime.parse(at, formatter);
    }

    /**
     * Convert the event into the specific string for data saving.
     *
     * @return the string representation in the specific format for database
     */
    @Override
    public String stringifyTask() {
        String timeFormat = this.at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        return String.format("E|%d|%s|%s", this.isDone ? 1 : 0, this.description, timeFormat);
    }

    /**
     * Return the string representation of the task.
     *
     * @return string representation of the task
     */
    @Override
    public String toString() {
        String timeFormat = this.at.format(DateTimeFormatter.ofPattern("HH:mm, dd MMM yyyy"));
        return "\u001B[35m(EVENT)\u001B[0m" + super.toString() + " (at: " + timeFormat + ")";
    }
}

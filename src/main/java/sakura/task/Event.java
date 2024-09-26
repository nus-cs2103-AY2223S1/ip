package sakura.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event as a task in the list.
 */
public class Event extends Task {

    protected LocalDateTime date;

    /**
     * Constructor for event.
     *
     * @param description description of the event.
     * @param date the time of the event in datetime format.
     */
    public Event(String description, String date) {
        super(description, date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.date = LocalDateTime.parse(date, formatter);
    }

    /**
     * Convert the event into the specific string for data saving.
     *
     * @return the string representation in the specific format for database
     */
    @Override
    public String stringifyTask() {
        String timeFormat = this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        return String.format("E|%d|%s|%s", this.isDone ? 1 : 0, this.description, timeFormat);
    }

    /**
     * Return the string representation of the task.
     *
     * @return string representation of the task
     */
    @Override
    public String toString() {
        String timeFormat = this.date.format(DateTimeFormatter.ofPattern("HH:mm, dd MMM yyyy"));
        return "(EVENT)" + super.toString() + " (at: " + timeFormat + ")";
    }
}
//[35m
//[0m
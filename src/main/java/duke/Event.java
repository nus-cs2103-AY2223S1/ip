package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event.
 */
public class Event extends Task {

    protected LocalDateTime time;
    protected String timeString;

    /**
     * Constructs a <code>Event</code> task.
     *
     * @param description Description of the task.
     * @param time Date and Time of the event in "dd/MM/yyyy HHmm" format.
     * @param isDone Indicator whether the task has been done or not.
     */
    public Event(String description, String time, boolean isDone) {
        super(description, isDone);
        this.timeString = time;
        this.time = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    /**
     * Returns the hour and minutes of this <code>Event</code> in a <code>String</code> in "HHmm" format.
     *
     * @return <code>String</code> representation of the hour and minutes of this <code>Event</code>.
     */
    public String getTime() {
        int len = timeString.length();
        return timeString.substring(len - 4);
    }

    /**
     * Returns the <code>String</code> representation of this <code>Event</code>.
     *
     * @return <code>String</code> representation of this <code>Event</code>.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time.getDayOfWeek() + " " + time.getDayOfMonth() + " " +
                time.getMonth() + " " + time.getYear() + " " + this.getTime() + ")";
    }

    /**
     * Returns the <code>String</code> representation of this <code>Event</code> in the format to be stored in the local
     * storage.
     *
     * @return <code>String</code> representation of this <code>Event</code> in the format to be stored in the local storage.
     */
    public String toStorageFormat() {
        char done = isDone ? '1' : '0';
        return "E" + " | " + done + " | " + this.description + " | " + this.timeString + "\n";
    }
}

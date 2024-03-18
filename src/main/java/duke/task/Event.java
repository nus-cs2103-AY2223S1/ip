package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that happens on a specific date.
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Initialises a <code>Event</code> task with its description and the date that it occurs.
     * @param name The description of the <code>Task</code>.
     * @param tag The tag of the <code>Task</code>.
     * @param at The date that the <code>Task</code> occurs on.
     */
    public Event(String name, String tag, LocalDate at) {
        super(name, tag);
        this.at = at;
    }

    /**
     * Returns the date that the <code>Event</code> occurs on.
     * @return The date that the <code>Event</code> occurs on.
     */
    public String getAt() {
        return this.at.toString();
    }

    /**
     * Returns a String with a different date format.
     * @return A <code>Event</code> with a different date format.
     */
    public String changeDateFormat() {
        String updatedDate = this.at.format(DateTimeFormatter.ofPattern("d MMM uuuu"));
        return "[E]" + super.toString()
                + "(at: " + updatedDate + ")";
    }

    /**
     * Returns the description of the <code>Event</code> with its date.
     * @return The description of the <code>Event</code> with its date.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + "(at: " + at + ")";
    }
}

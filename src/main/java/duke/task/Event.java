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
     * @param at The date that the <code>Task</code> occurs on.
     */
    public Event(String name, LocalDate at) {
        super(name);
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
     * Changes the date format of the date that the <code>Event</code> occurs on.
     */
    public void changeDateFormat() {
        this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
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

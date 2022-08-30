package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

/**
 * Represents a task that starts at a specific date.
 */
public class Event extends Task {

    public static final String EVENT_REP = "E";
    protected LocalDate time;

    /**
     * Constructs a new Event.
     *
     * @param content Description of the Event.
     * @param time Start date of the task.
     * @throws DukeException If the date is invalid.
     */
    public Event(String content, String time) throws DukeException {
        super(content);
        try {
            this.time = LocalDate.parse(time);
        } catch (DateTimeParseException e) {
            throw new DukeException("You need to input in yyyy-mm-dd format!");
        }
    }

    /**
     * Returns a String representation of the Event.
     *
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        return "[" + EVENT_REP + "]" + super.toString()
                + " (at: " + this.time.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Returns a String representation of the Event for storage.
     *
     * @return String representation of the Event for storage.
     */
    @Override
    public String toStorage() {
        return EVENT_REP + super.toStorage() + Task.SEPARATOR + this.time;
    }
}

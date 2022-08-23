package duke.task;

import duke.utilities.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class.
 */
public class Event extends Task {
    /** Events have a starting time. */
    protected LocalDateTime start;

    /** Events have an ending time. */
    protected LocalDateTime end;

    /**
     * Constructor for Event objects.
     * @param description The description of the event task.
     * @param start Start time of event.
     * @param end End time of event.
     * @throws DukeException For Duke related exceptions.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) throws DukeException {
        super(description);
        this.start = start;
        this.end = end;
        if (description.isBlank()) {
            throw new DukeException("The description of event task is missing information!");
        }
    }

    /**
     * Getter for the date of event.
     * @return Returns the date of the event.
     */
    public String getStart() {
        return this.start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public String getEnd() {
        return this.end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * String representation of Event task object.
     * @return Returns the String representation of the current object.
     */
    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString() + " (at: " +
                this.start.format(format) + " to " + this.end.format(format) + ")";
    }
}

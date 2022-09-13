package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.utilities.DukeException;

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
     *
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
     * Gets the start time of event.
     *
     * @return Returns the date of the event in String format.
     */
    public String getStart() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return this.start.format(pattern);
    }

    /**
     * Gets the end time of event.
     *
     * @return Returns the date of the event in String format.
     */
    public String getEnd() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return this.end.format(pattern);
    }

    /**
     * String representation of Event task object.
     *
     * @return Returns the String representation of the current object.
     */
    @Override
    public String toString() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString() + " (at: "
                + this.start.format(pattern) + " to " + this.end.format(pattern) + ")";
    }
}

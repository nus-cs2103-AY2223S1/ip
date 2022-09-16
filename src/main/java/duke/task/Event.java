package duke.task;

import duke.exception.DukeException;
import duke.exception.DukeInvalidDateTimeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task with an event date and time.
 *
 * @author Lim Ai Lin
 */
public class Event extends Task {

    private final LocalDateTime DATE;
    private final String AT;

    /**
     * Creates a new unmarked Event task object with an event date and time.
     *
     * @param description The name of the Event task.
     * @param at The date and time at which the event is happening.
     * @throws DukeException
     *          Thrown when the wrong date and time format is entered.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy HH:mm");
        this.AT = at;
        try {
            this.DATE = LocalDateTime.parse(at, formatter);
        } catch (Exception e) {
            throw new DukeInvalidDateTimeException();
        }
    }

    /**
     * Gets the date and time of the Event task.
     *
     * @return The date and time of the task.
     */
    public String getAt() {
        return AT;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + DATE.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mma")) + ")";
    }
}

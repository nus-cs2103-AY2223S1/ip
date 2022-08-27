package duke.task;

import duke.utilities.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates an event.
 */
public class Event extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructs a new {@code Event} with given description and time.
     * @param description The description of the event.
     * @param start The start time of the event.
     * @param end The end time of the event.
     * @throws DukeException
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) throws DukeException {
        super(description);
        if (description.equals("")) {
            throw new DukeException("The description of a event cannot be empty.");
        }
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the string representation of this {@code start} for display.
     * @return The string representation of this {@code start} for display.
     */
    public String getStart() {
        return this.start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Returns the string representation of this {@code end} for display.
     * @return The string representation of this {@code end} for display.
     */
    public String getEnd() {
        return this.end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString() + " (at: " +
                this.start.format(format) + " to " + this.end.format(format) + ")";
    }
}

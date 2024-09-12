package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

/**
 * Represent a task that is happening at a specific date/time.
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Creates an Event instance.
     * @param description Description of the Event.
     * @param at Date/Time of the Event.
     * @throws DukeException If the date/time specified is invalid.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            this.at = LocalDate.parse(at);

        } catch (DateTimeParseException exception) {
            throw new DukeException("Date should be in yyyy-mm-dd format.");
        }
    }

    /**
     * Returns the string representation of Event to be stored.
     * @return A String representation of Event to be stored in Storage.
     */
    @Override
    public String toStorageString() {
        String taskString = super.toStorageString();
        return "E" + Task.STORAGE_DELIMITER + taskString + Task.STORAGE_DELIMITER + this.at;
    }

    /**
     * Returns the string representation of Event to be displayed.
     * @return A String representation of Event to be displayed.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }
}

package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

/**
 * Event is a task that happens at a specific date/time.
 */
public class Event extends Task {
    protected LocalDate date;

    /**
     * Constructor for Event.
     *
     * @param description Description of the Event.
     * @param date The date/time when the Event happens.
     * @throws DukeException If the date is invalid.
     */
    public Event(String description, String date) throws DukeException {
        super(description);
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date should be in yyyy-mm-dd format.");
        }
    }

    /**
     * Returns the String representation of the Event.
     *
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        return ("[E]" + super.toString() + " (at: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
    }

    /**
     * Returns the String representation to be stored.
     *
     * @return String storage representation of the Event.
     */
    @Override
    public String toStorageString() {
        String doneDescriptionString = super.toStorageString();
        return "E" + Task.STORAGE_DELIMITER
                + doneDescriptionString + Task.STORAGE_DELIMITER
                + this.date;
    }
}

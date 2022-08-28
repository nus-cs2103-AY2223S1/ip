package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

/**
 * Deadline is a task that is due by a specific date/time.
 */
public class Deadline extends Task {
    protected LocalDate date;

    /**
     * Constructor for Deadline.
     *
     * @param description Description of the Deadline.
     * @param date The date/time when the Deadline is due.
     * @throws DukeException If the date is invalid.
     */
    public Deadline(String description, String date) throws DukeException {
        super(description);
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date should be in yyyy-mm-dd format.");
        }
    }

    /**
     * Returns the String representation of the Deadline.
     *
     * @return String representation of the Deadline.
     */
    @Override
    public String toString() {
        return ("[D]" + super.toString() + " (by: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
    }

    /**
     * Returns the String representation to be stored.
     *
     * @return String storage representation of the Deadline.
     */
    @Override
    public String toStorageString() {
        String doneDescriptionString = super.toStorageString();
        return "D" + Task.STORAGE_DELIMITER
                + doneDescriptionString + Task.STORAGE_DELIMITER
                + this.date;
    }
}

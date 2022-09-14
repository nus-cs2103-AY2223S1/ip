package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

/**
 * Represent a task that is due by a specific date/time.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Creates a Deadline instance.
     * @param description Description of the Deadline.
     * @param by Date/Time that this Deadline is due.
     * @throws DukeException If the due date/time specified is invalid.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException exception) {
            throw new DukeException("Date should be in yyyy-mm-dd format.");
        }
    }

    /**
     * Returns the string representation of Deadline to be stored.
     * @return A String representation of Deadline to be stored in Storage.
     */
    @Override
    public String toStorageString() {
        String taskString = super.toStorageString();
        return "D" + Task.STORAGE_DELIMITER + taskString + Task.STORAGE_DELIMITER + this.by;
    }

    /**
     * Returns the string representation of Deadline to be displayed.
     * @return A String representation of Deadline to be displayed.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }
}

package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates Deadline which is-a Task
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructs an instance of Deadline which inherits from Task.
     *
     * @param description Deadline's description.
     * @param by Deadline's by.
     * @throws DukeException if Deadline's by is empty.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        if (by.equals(" ")) {
            throw new DukeException("The deadline needs to have a end day and/or date nya.");
        }
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date after /by should be in YYYY-MM-DD Format nya.");
        }
    }

    @Override
    public void updateDate(String updatedBy) throws DukeException {
        try {
            this.by = LocalDate.parse(updatedBy);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date after task number should be in YYYY-MM-DD Format nya.");
        }

    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Class constructor for Deadline.
     *
     * @return string that represent this deadline object, to be stored in data file.
     */
    @Override
    public String toStorageString() {
        return "D" + super.toStorageString() + " | " + this.by;
    }
}

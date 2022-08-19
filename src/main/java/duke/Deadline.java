package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulate Deadline which is-a Task
 *
 * @author: Jonas Png
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Class constructor for Deadline.
     *
     * @param description Deadline's description.
     * @param by Deadline's by.
     * @throws DukeException if Deadline's by is empty.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        if (by.equals(" ")) {
            throw new DukeException("☹ OOPS!!! The deadline needs to have a end day and/or date");
        }
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("date after /by should be in YYYY-MM-DD Format.");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toStorageString() {
        return "D" + super.toStorageString() + " | " + this.by;
    }
}

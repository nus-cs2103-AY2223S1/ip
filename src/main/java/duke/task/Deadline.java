package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate by;
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException exception) {
            throw new DukeException("Date should be in yyyy-mm-dd format.");
        }
    }

    @Override
    public String toStorageString() {
        String taskString = super.toStorageString();
        return "D" + Task.STORAGE_DELIMITER + taskString + Task.STORAGE_DELIMITER + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }
}

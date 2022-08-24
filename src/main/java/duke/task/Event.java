package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{

    protected LocalDate at;

    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            this.at = LocalDate.parse(at);

        } catch (DateTimeParseException exception) {
            throw new DukeException("Date should be in yyyy-mm-dd format.");
        }
    }

    @Override
    public String toStorageString() {
        String taskString = super.toStorageString();
        return "E" + Task.STORAGE_DELIMITER + taskString + Task.STORAGE_DELIMITER + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }
}

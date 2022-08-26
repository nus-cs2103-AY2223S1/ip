package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import duke.DukeException;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong date/time format. Please use this format: yyyy-mm-dd");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formattedDate() + ")";
    }

    private String formattedDate() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}

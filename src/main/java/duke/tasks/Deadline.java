package duke.tasks;

import duke.exceptions.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private final LocalDate by;

    public Deadline(String description, LocalDate by) throws DukeException {
        super(description);
        this.by = by;
    }

    @Override
    public String encode() {
        return "D | " + super.encode() + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}

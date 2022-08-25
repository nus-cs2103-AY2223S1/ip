package duke.task;

import duke.DukeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException exception) {
            throw new DukeException("Please enter the date in the format of yyyy-mm-dd");
        }
    }

    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    public LocalDate getBy() {
        return by;
    }

    @Override
    public String getType() { return "D"; }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

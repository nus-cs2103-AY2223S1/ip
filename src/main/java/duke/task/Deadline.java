package duke.task;

import duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate date;
    private final String by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        this.by = by;
        try {
            this.date = LocalDate.parse(by, formatter);
        } catch (Exception e) {
            throw new DukeException("Please enter date in the format: dd/M/yyyy");
        }
    }

    public String getBy() { return by; }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

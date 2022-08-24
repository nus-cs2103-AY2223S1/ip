package duke.task;

import java.time.LocalDate;

import duke.DukeException;
import duke.util.Parser;


public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, String by) throws DukeException {
        this(description, isDone, Parser.parseDate(by));
    }

    public Deadline(String description, String by) throws DukeException {
        this(description, false, Parser.parseDate(by));
    }

    String getFormattedDateString() {
        return this.by.format(DATE_FORMATTER);
    }

    @Override
    public String getStorageFormat() {
        return "D | " + super.getStorageFormat() + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getFormattedDateString() + ")";
    }
}

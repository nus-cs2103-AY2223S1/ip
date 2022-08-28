package unc.task;

import unc.UncException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) throws UncException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException exception) {
            throw new UncException("Invalid datetime");
        }
    }

    public Deadline(String description, String by, String done) throws UncException {
        super(description, done == "true");
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException exception) {
            throw new UncException("Invalid datetime");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + ")";
    }

    @Override
    public String toStorageString() {
        return "D" + "///" + this.description + "///" + this.by + "///" + this.isDone;
    }
}

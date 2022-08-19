package duke.task;

import duke.DukeException;

import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate by;

    Deadline(String description, boolean isComplete, LocalDate by) {
        super(description, isComplete);
        this.by = by;
    }

    Deadline(String description, LocalDate by) {
        this(description, false, by);
    }

    public Deadline(String description, boolean isComplete, String by) throws DukeException {
        this(description, isComplete, Task.parseDate(by));
    }

    public Deadline(String description, String by) throws DukeException {
        this(description, false, Task.parseDate(by));
    }

    LocalDate getDate() {
        return this.by;
    }

    String getFormattedDate() {
        return this.getDate().format(DATE_FORMAT);
    }

    @Override
    public String toStorageFormat() {
        return "D | " + super.toStorageFormat() + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getFormattedDate() + ")";
    }
}

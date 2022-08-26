package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDate by, boolean done) {
        super(description);
        this.by = by;
        this.isDone = done;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String save() {
        return "D | " + (isDone ? "1" : "0") + " | " + this.description + " | " + this.by.toString();
    }

    @Override
    public LocalDate getTime() {
        return this.by;
    }
}

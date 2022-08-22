package duke.task;

import java.time.LocalDate;

public class Deadline extends Task {
    private final LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toStorage() {
        return "D|" + super.toStorage() + "|" + this.by.format(Deadline.inputDateFormat);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(Deadline.outputDateFormat) + ")";
    }
}

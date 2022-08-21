package duke.task;

import java.time.LocalDate;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by, Task.inputFormat);
    }

    @Override
    public String toStorage() {
        return "D | " + super.toStorage() + " | " + this.by.format(Task.inputFormat);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(Task.outputFormat) + ")";
    }
}

package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description);
        this.by = LocalDate.parse(by);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(), this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String saveTask() {
        return String.format("D | %s | %s", super.saveTask(), this.by);
    }
}
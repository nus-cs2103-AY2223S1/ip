package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(), by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String saveTask() {
        return String.format("D | %s | %s", super.saveTask(), by);
    }
}
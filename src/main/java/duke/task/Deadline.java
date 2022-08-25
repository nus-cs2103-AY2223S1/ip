package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String taskDescription, LocalDate by) {
        super(taskDescription);
        this.by = by;
    }

    public Deadline(String taskDescription, LocalDate by, Boolean isDone) {
        super(taskDescription);
        this.by = by;
        this.isDoneSetter(isDone);
    }

    @Override
    protected String getTypeIcon() {
        return "[D]";
    }

    @Override
    public String toStorageString() {
        return "D" + "|" + super.toStorageString() + "|" + by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

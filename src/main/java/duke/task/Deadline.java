package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate byDate;

    public Deadline(String desc, LocalDate byDate) {
        super(desc);
        this.byDate = byDate;
    }

    public String getDeadlineBy() {
        return this.byDate.toString();
    }

    @Override
    public String getDescription() {
        return super.description;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String getTaskType() {
        return "D";
    }
}

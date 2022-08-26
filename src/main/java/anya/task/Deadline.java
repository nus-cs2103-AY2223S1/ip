package anya.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime completeBy;

    public Deadline(String name, LocalDateTime completeBy) {
        super(name);
        this.completeBy = completeBy;
    }

    @Override
    public String toString() {
        String dateTime = completeBy.format(DateTimeFormatter.ofPattern("MMM dd yyyy',' hh'.'mma"));
        return "[D]" + super.toString() + " (by: " + dateTime + ")";
    }

    @Override
    public String toSave() {
        String doneVar = super.isDone ? "1" : "0";
        String dateTime = completeBy.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        return "D | " + doneVar + " | " + super.name + " | " + dateTime;
    }
}

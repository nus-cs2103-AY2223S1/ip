package task;

import task.Task;

import java.time.LocalDate;

public class Deadline extends Task {

    protected LocalDate localDate;

    public Deadline(String description, LocalDate localDate) {
        super(description);
        this.localDate = localDate;
    }

    public Deadline(String description, LocalDate localDate, boolean isDone) {
        super(description, isDone);
        this.localDate = localDate;
    }

    @Override
    public String encode() {
        return "D" + Task.ENCODING_SEPARATOR + localDate + Task.ENCODING_SEPARATOR + super.encode();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + localDate + ")";
    }
}
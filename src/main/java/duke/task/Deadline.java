package duke.task;

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
        return "D" + ENCODING_SEPARATOR + localDate + ENCODING_SEPARATOR + super.encode();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + localDate + ")";
    }
}
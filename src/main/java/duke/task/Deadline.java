package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    private String dateToString() {
        return by.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
    }

    @Override
    public String textFormat() {
        return "D|" + (isDone ? 1 : 0) + "|" + description + "|" + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateToString() + ")";
    }
}

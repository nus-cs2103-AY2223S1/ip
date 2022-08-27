package duke.task;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private ZonedDateTime by;

    public Deadline(String description, ZonedDateTime by) {
        super(description);
        this.by = by;
    }

    public ZonedDateTime getBy() {
        return by;
    }

    @Override
    public char getType() {
        return 'D';
    }

    @Override
    public String toString() {
        return String.format(
                "[D]%s (by: %s)",
                super.toString(),
                this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mma"))
        );
    }
}

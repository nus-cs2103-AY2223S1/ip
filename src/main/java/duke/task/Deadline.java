package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;
    protected LocalDateTime byDate;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDateTime byDate) {
        super(description);
        this.byDate = byDate;
    }

    public String getOutput() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description, by);
    }

    @Override
    public String toString() {
        if (byDate != null) {
            return "[D]" + super.toString() + " (by: " + byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }
}

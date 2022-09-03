package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime byDate;

    public Deadline(String description, LocalDateTime byDate) {
        super(description);
        this.byDate = byDate;
    }

    @Override
    public String saveText() {
        return String.format("%d deadline %s /by %s", this.isDone ? 1 : 0, this.description, this.byDate);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class TaskWithDate extends Task {
    private LocalDateTime by;

    public TaskWithDate(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    protected String dateToString() {
        return by.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
    }

    protected LocalDateTime getTiming() {
        return this.by;
    }
}

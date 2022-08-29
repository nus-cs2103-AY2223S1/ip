package duke.task;

import java.time.LocalDateTime;

public class Deadlines extends Task{
    protected LocalDateTime by;

    public Deadlines(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }
    
    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DATE_TIME_FORMATTER) + ")";
    }
}

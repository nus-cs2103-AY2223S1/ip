package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime atDate;

    public Event(String description, LocalDateTime atDate) {
        super(description);
        this.atDate = atDate;
    }

    @Override
    public String saveText() {
        return String.format("%d event %s /at %s", this.isDone ? 1 : 0, this.description, this.atDate);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
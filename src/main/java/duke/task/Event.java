package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public Event(String description, boolean isDone, LocalDate at) {
        super(description);
        this.at = at;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)",
                super.toString(), this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String saveTask() {
        return String.format("E | %s | %s", super.saveTask(), this.at);
    }
}
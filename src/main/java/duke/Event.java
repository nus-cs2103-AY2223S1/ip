package duke;

import java.time.LocalDate;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String toFileString() {
        return String.format("E | %d | %s | %s", isDone ? 1 : 0, description, at.toString());
    }
}

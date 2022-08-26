package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate at;
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String stringify() {
        return String.format("E##%s##%s", super.stringify(), this.at);
    }
    @Override
    public String toString() {
        return String.format("[E]%s(at: %s)", super.toString(), this.at.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
    }
}
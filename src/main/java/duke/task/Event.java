package duke.task;

import java.time.LocalDate;

public class Event extends Task {
    private final LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toStorage() {
        return "E|" + super.toStorage() + "|" + this.at.format(Event.INPUT_DATE_FORMAT);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at.format(Event.OUTPUT_DATE_FORMAT) + ")";
    }
}

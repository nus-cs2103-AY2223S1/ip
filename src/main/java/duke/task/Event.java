package duke.task;

import java.time.LocalDate;

public class Event extends Task {

    protected LocalDate localDate;

    public Event(String description, LocalDate date) {
        super(description);
        this.localDate = date;
    }

    public Event(String description, LocalDate localDate, boolean isDone) {
        super(description, isDone);
        this.localDate = localDate;
    }

    @Override
    public String encode() {
        return "E" + Task.ENCODING_SEPARATOR + localDate + Task.ENCODING_SEPARATOR + super.encode();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + localDate + ")";
    }
}
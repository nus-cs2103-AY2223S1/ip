package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate datetime;

    public Event(String description, LocalDate datetime) {
        super(description);
        this.datetime = datetime;
    }

    public Event(String isDone, String description, LocalDate datetime) {
        super(isDone, description);
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + datetime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toTxt() {
        return String.format("E @@ %s @@ %s", super.toTxt(), datetime);
    }
}
package duke.tasks;

import duke.utils.DateTime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private static final DateTimeFormatter formatter = DateTime.formatter;
    private final LocalDateTime time;

    public Event(String description, String time) {
        super(description);
        this.time = LocalDateTime.parse(time, formatter);
    }

    public Event(boolean isMarked, String description, String time) {
        super(isMarked, description);
        this.time = LocalDateTime.parse(time);
    }

    @Override
    public String dbRepresentation() {
        return String.join("|", "E", Boolean.toString(isMarked),  description, time.toString());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.time.format(formatter) + ")";
    }
}

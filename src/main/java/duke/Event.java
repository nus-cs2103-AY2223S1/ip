package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate time;

    public Event(String description, String time) {
        super(description);
        this.time = LocalDate.parse(time);
        type = 'E';
    }

    public Event(String description, boolean isDone, String time) {
        this(description, time);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toData() {
        return super.toData() + ", " + time;
    }
}

package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;
    protected LocalTime time;

    public Event(String description, boolean isDone, String at, String time) {
        super(description, isDone);
        this.at = LocalDate.parse(at);
        this.time = LocalTime.parse(time);
    }

    @Override
    public String toString() {
        return "E | " + this.getStatusIcon() + " | " + this.getDescription() + " | " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + this.time.format(DateTimeFormatter.ofPattern("hhmma"));
    }
}

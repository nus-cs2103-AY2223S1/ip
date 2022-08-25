package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDateTime at;

    public Event(String title, LocalDateTime at) {
        super(title);
        this.at = at;
    }

    public Event(String title, LocalDateTime at, Boolean isDone) {
        super(title, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");

        return "[E] " + super.toString() + " (at: " + at.format(formatter) + ")";
    }

    public String toSaveString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        return "E|" + super.toSaveString() + "|" + at.format(formatter);
    }
}

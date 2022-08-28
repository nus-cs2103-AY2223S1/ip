package data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate dateAt;

    public Event(String title, boolean done, LocalDate dateAt) {
        super(title, done);
        this.dateAt = dateAt;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + dateAt.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

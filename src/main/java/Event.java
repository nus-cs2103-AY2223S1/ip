package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate time;

    public Event(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + String.format(" (at: %s)", time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String toData() {
        return "E" + super.toData() + this.time.toString();
    }
}

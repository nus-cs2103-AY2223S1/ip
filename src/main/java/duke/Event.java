package main.java.duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String action, LocalDateTime at) {
        super(action);
        this.at = at;
    }

    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        return at.format(formatter);
    }

    public String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return at.format(formatter);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (at: " + this.getDate() + " " + this.getTime() + ")";
    }
}

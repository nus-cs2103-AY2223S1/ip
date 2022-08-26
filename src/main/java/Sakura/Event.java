package Sakura;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, String at) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime dateFormat = LocalDateTime.parse(at, formatter);
        this.at = dateFormat;
    }

    @Override
    public String stringifyTask() {
        String timeFormat = this.at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        return String.format("E|%d|%s|%s", this.isDone ? 1 : 0, this.description, timeFormat);
    }

    @Override
    public String toString() {
        String timeFormat = this.at.format(DateTimeFormatter.ofPattern("HH:mm, dd MMM yyyy"));
        return "\u001B[35m(EVENT)\u001B[0m" + super.toString() + " (at: " + timeFormat + ")";
    }
}

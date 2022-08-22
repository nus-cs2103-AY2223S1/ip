package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDateTime eventDateTime;

    public Event(String description, String eventDateTime) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.eventDateTime = LocalDateTime.parse(eventDateTime, formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String formatted = this.eventDateTime.format(formatter);
        return String.format("[E]%s (on: %s)", super.toString(), formatted);
    }
}

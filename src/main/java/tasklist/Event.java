package tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
    private final LocalDateTime time;

    public Event(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[E][X] " + this.description + " (at: " + this.time.format(DF) + ")";
        } else {
            return "[E][ ] " + this.description + " (at: " + this.time.format(DF) + ")";
        }
    }

    @Override
    public String toStringText() {
        return "E | " + this.description + " | " + this.isDone + " | " + this.time.format(DF);
    }
}

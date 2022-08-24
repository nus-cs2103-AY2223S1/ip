package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDateTime at;

    public Event(String description, boolean done, String at) {
        super(description, done);
        this.at = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getFormattedDetail() + ")";
    }

    @Override
    public char getType() {
        return 'E';
    }

    @Override
    public String getOriginalDetail() {
        return at.toString();
    }

    @Override
    public String getFormattedDetail() {
        return at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }
}
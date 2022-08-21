import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime eventDateTime;
    public Event(String description, boolean isDone, LocalDateTime eventDateTime) {
        super(description, isDone);
        this.eventDateTime = eventDateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateTimeParser.changeDateTimeFormat(eventDateTime) + ")";
    }
}
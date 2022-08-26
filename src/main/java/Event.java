import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate eventTime;

    public Event(String description, LocalDate eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    public Event(boolean isDone, String description, LocalDate eventTime) {
        super(isDone, description);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventTime.format(DateTimeFormatter.ofPattern("dd MMM yy")) + ")";
    }

    public String toDbString() {
        return "E" + " | " + super.toDbString() + " | " + eventTime.format(DateTimeFormatter.ofPattern("dd MMM yy"));
    }
}

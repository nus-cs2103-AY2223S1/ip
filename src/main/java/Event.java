import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate eventTime;

    public Event(String description, LocalDate eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventTime.format(DateTimeFormatter.ofPattern("dd MMM yy")) + ")";
    }
}

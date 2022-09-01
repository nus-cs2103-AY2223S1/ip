import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate eventDate;
    protected LocalTime eventTime;

    Event(String description, String eventDate, String eventTime) {
        super(description);
        this.eventDate = LocalDate.parse(eventDate);
        this.eventTime = LocalTime.parse(eventTime);
    }

    public String getDue() {
        return this.time;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " " + eventTime + ")";
    }
}

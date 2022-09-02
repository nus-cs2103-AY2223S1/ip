import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime eventTime;

    public Event(String description, LocalDateTime eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String encode() {
        return "E | " + super.encode() + "| " + this.eventTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(on: " + eventTime.format(Task.DATE_TIME_DISPLAY_FORMATTER) + ")";
    }
}

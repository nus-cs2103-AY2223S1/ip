import java.time.LocalDateTime;

public class Event extends Task {
    private final LocalDateTime startTime;

    public Event(String name, String startTimeAsText) {
        super(name);
        this.startTime = LocalDateTime.parse(startTimeAsText);
    }

    @Override
    public String toString() {
        return String.format("[E] %s (at %s)", super.toString(), this.startTime.toString());
    }
}
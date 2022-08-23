import java.time.LocalDateTime;

public class Event extends Task {
    private final LocalDateTime startTime;

    public Event(String name, boolean initialComplete, String startTimeAsText) {
        super(name, initialComplete);
        this.startTime = LocalDateTime.parse(startTimeAsText);
    }

    @Override
    public String toString() {
        return String.format("[E] %s (at %s)", super.toString(), this.startTime.toString());
    }

    @Override
    public String toFileRepresentation() {
        return "E" + "|" + (this.isComplete() ? "1" : "0") + "|" + this.getName() + "|" + this.startTime.toString();
    }
}
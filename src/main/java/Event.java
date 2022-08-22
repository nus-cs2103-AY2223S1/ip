import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime dateTime;

    public Event(String description, LocalDateTime dateTime) {
        super(description, TaskType.EVENT);
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (at: %s)", super.getTaskIcon(), super.toString(), Parser.displayDateTime(this.dateTime));
    }
}

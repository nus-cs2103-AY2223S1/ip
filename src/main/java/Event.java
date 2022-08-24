import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime timing;

    public Event(String description, LocalDateTime timing) {
        super(description);
        this.timing = timing;
    }

    public String getTiming() {
        DateTimeFormatter dTF = DateTimeFormatter.ofPattern("dd MMM uuuu HH:mm");
        return dTF.format(timing).toString();
    }

    @Override
    public String toString() {
        return String.format("[E] %s %s (at: %s)", getStatusIcon(), getDescription(), getTiming());
    }
}

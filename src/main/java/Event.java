import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    public String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mma"));
    }

    @Override
    public String toString() {
        String formatted = formatDateTime(at);
        return String.format("[E]%s (at: %s)", super.toString(), formatted);
    }
}
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class Event extends Task {
    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;

    public Event(String taskDescription, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(taskDescription);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        String formattedStartDateTime = this.startDateTime.format(DateTimeFormatter.ofPattern("MMM dd uuuu, HHmm").withResolverStyle(ResolverStyle.STRICT.STRICT));
        String formattedEndDateTime = this.endDateTime.format(DateTimeFormatter.ofPattern("MMM dd uuuu, HHmm").withResolverStyle(ResolverStyle.STRICT.STRICT));
        return String.format("[E] %s (at: %s to %s)", super.toString(), formattedStartDateTime, formattedEndDateTime);
    }
}

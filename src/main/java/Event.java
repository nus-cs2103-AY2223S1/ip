import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private final LocalDateTime time;

    public Event(String command, LocalDateTime time) {
        super(command);
        this.time = time;
    }

    @Override
    public String toString() {
        if (done) {
            return "[E][X] " + this.description + " (by: " +
                    this.time.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm a")) + ")";
        } else {
            return "[E][ ] " + this.description + " (by: " +
                    this.time.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm a")) + ")";
        }
    }

    @Override
    public String toStringText() {
        return "E | " + this.done + " | " + this.description + " | " + this.time;
    }
}
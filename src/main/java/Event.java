import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toStringFileFormat() {
        return "E | " + super.toStringFileFormat() + " | " + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at.format(DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm")) + ")";
    }
}

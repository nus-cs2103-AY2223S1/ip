import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime atDateTime;

    public Event(String description, LocalDateTime atDateTime) {
        super(description);
        this.atDateTime = atDateTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
        String formattedDateTime = this.atDateTime.format(formatter);
        return String.format("[E]%s (by: %s)", super.toString(), formattedDateTime);
    }

    @Override
    public String toFileFormatString() {
        return "E" + super.toFileFormatString() + description + "|" + atDateTime;
    }
}
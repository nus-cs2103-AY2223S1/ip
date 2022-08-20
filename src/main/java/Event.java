import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;


public class Event extends Task {
    String at;

    public Event(String description, String at) throws DateTimeParseException {
        super(description);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy | E | h:mma");
        LocalDateTime dateTime = LocalDateTime.parse(at.trim(), inputFormatter);

        this.at = dateTime.format(outputFormatter);
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

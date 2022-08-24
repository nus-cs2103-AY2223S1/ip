import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class Event extends Task{
    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        String formattedAt = at.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
        return "[E]" + super.toString() + " (at: " + formattedAt + ")";
    }
}

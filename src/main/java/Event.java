import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime dateTime;
    protected static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd LLL yyyy hh:mma");

    public Event(String description, String at) {
        super(description);
        this.dateTime = LocalDateTime.parse(at, inputFormatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTime.format(outputFormatter) + ")";
    }
}
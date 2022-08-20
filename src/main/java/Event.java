import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task{
    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + at.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)) + ")";
    }
}

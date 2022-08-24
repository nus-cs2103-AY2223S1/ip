import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {

    protected LocalDateTime dateTime;

    public Event(String description, String dateTime) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        this.dateTime =  LocalDateTime.parse(dateTime,formatter);
    }

    public String getDate() {
        return this.dateTime.format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.FULL));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTime.format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.FULL)) + ")";
    }
}

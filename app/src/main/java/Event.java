import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    public LocalDate time;

    public Event(String details, String time) {
        super(details);
        this.time = LocalDate.parse(time);
    }

    @Override
    public String getTaskIcon() {
        return "E";
    }

    @Override
    public String getDetails() {
        return details + " (at: " + time.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }

    @Override
    public String getEncodedDetails() {
        return details + "|" + time;
    }
}

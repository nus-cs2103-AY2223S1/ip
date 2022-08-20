import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class EventTask extends Task {

    protected LocalDate eventDate;

    public EventTask(String description, String eventDate) {
        super(description);
        this.eventDate = LocalDate.parse(eventDate);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (at: " + this.eventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

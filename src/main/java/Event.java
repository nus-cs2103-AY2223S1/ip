import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String at;
    protected LocalDate date;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.date = LocalDate.parse(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

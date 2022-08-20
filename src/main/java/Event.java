import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
public class Event extends Task {

    protected LocalDate at;

    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM-d-yyyy")) + ")";
    }
    @Override
    public String getSaveString() {
        return "E | " + (isDone ? "1 | " : "0 | " + this.description + " | " + this.at);
    }
}

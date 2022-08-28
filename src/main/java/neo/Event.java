package neo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        LocalDate e1 = LocalDate.parse(at);
        return "[E]" + super.toString() + " (on: " + e1.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
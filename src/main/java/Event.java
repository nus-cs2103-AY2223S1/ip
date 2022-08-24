import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate period;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Event(String description, LocalDate period) {
        super(description);
        this.period = period;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.period.format(dtf) + ")";
    }

    @Override
    public String stringFormatting() {
        return "E" + super.stringFormatting() + " # " + this.period;
    }
}

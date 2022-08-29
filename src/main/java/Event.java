import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate date;

    public Event(String description, String date) {
        super(description);
        this.date = LocalDate.parse(date);
    }

    // prints date in MMM d yyyy format
    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + getDate() + ")";
    }
}

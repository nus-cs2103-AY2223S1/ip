package Duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate date;

    public Event(String description, LocalDate eventDate) {
        super(description);
        this.date = eventDate;
    }

    @Override
    public String toString() {
        String eventTime = "";
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d YYY")) + ")";
    }
}
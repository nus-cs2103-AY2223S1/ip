import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime date;

    public Event(String description, String dateStr) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.date = LocalDateTime.parse(dateStr, formatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getDateStr() + ")";
    }

    public String getDateStr() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EE, hh:mma dd MMMM yyyy");
        return this.date.format(formatter);
    }

    @Override
    public String save() {
        return "E" + super.save() + " | " + this.date;
    }
}

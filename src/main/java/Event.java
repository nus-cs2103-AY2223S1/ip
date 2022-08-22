import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate date;

    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public String stringToSave() {
        return "E|" + ("X".equals(super.getStatusIcon()) ? "1|" : "0|") + super.description + "|" + this.date;
    }
    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.description + " (at: " +
                this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

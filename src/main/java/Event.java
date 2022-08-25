import java.time.LocalDate;

public class Event extends Task {
    private LocalDate eventDate;
    public Event(String description, LocalDate eventDate) {
        super(description);
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventDate + ")";
    }
}
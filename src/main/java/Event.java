import java.time.LocalDate;

public class Event extends Task {
    private final LocalDate date;

    public Event(String description, String dateStr) {
        super(description);
        this.date = LocalDate.parse(dateStr);
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", this.isDone ? "X" : " ", this.description, this.date);
    }
}

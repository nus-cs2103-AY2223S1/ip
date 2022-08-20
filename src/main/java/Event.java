import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public boolean isOn(LocalDate date) {
        return this.at.equals(date);
    }

    @Override
    public String toString() {
        return String.format("[E] %s (at: %s)", super.toString(), this.at.format(DateTimeFormatter.ofPattern("E, d MMM yyyy")));
    }
}

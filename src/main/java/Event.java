import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDate date;

    public Event(String description, LocalDate date) {
        super(description, "E");
        this.date = date;
    }

    public Event(String description, String done, LocalDate date) {
        super(description, done, "E");
        this.date = date;
    }

    public LocalDate getDateline() {
        return this.date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}

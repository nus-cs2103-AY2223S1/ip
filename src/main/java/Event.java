import java.time.LocalDate;

public class Event extends Task {

    protected LocalDate date;

    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}
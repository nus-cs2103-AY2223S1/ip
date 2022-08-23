import java.time.LocalDate;

public class Event extends Task {
    protected LocalDate duration;

    public Event(String description, String duration) {
        super(description);
        this.duration = LocalDate.parse(duration);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + duration + ")";
    }
}

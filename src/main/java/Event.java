import java.time.LocalDate;

public class Event extends Task {
    protected String at;
    protected LocalDate date;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.date = LocalDate.parse(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date.getMonth()
                + " " + this.date.getDayOfMonth() + " " + this.date.getYear() + ")";
    }
}

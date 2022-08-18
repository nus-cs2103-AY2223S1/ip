import java.time.LocalDate;

public class Event extends Task {
    private LocalDate date;

    public Event(String msg, LocalDate date) {
        super(msg);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("%s%s (at: %s)", "[E]", super.toString(), this.date.toString());
    }

}

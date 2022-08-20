import java.time.LocalDate;
import java.time.format.*;

public class Event extends Task{
    private LocalDate date;

    public Event(String task, LocalDate date) {
        super(task);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("%s %s (at: %s)", "[E]", super.toString(), this.date.toString());
    }
}

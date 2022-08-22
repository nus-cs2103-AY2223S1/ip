import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime date;

    public Event(String description, String date) throws DateTimeException {
        super(description);
        this.date = DateParser.parseToDate(date);
    }

    @Override
    public String toString() {
        return "[" + TaskType.E + "]" + super.toString() + " (at: "
                + DateParser.dateToString(this.date) + ")";
    }
}

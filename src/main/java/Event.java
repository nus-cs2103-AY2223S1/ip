import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends DatedTask {
    protected LocalDate date;
    protected String at;
    public Event(String description, String at) throws DateTimeException {
        super(description, LocalDate.parse(at));
        this.date = LocalDate.parse(at);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

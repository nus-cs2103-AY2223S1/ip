import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Event extends Task{

    private LocalDate date;

    public Event(String item, String dateString) {
        super(item);
        this.date = LocalDate.parse(dateString);
    }

    private String dateFormat(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " at: " + dateFormat(date);
    }
}

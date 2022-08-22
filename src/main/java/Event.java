import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    protected String at;
    protected LocalDate date;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        try {
            this.date = LocalDate.parse(at);
            this.at = this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid Date format! Use a YYYY-MM-DD format!");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

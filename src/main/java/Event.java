import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Events are tasks that start at a specific time and ends at a specific time e.g., team project meeting on 2/10/2019 2-4pm
 */
public class Event extends Task {

    protected LocalDate at;

    public Event(String description, String at) throws InvalidEventException {
        super(description);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new InvalidEventException();
        }
    }

    public String getFormattedDate() {
        return this.at.format(DateTimeFormatter.ofLocalizedDate(Task.DATE_FORMAT));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + this.getFormattedDate()
                + ")";
    }
}
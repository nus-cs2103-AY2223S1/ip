import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event Class that encapsulates details of an Event Task.
 *
 * @author Elgin Lee
 */
public class Event extends Task {
    /** The date and time of the event. */
    protected LocalDate date;

    /**
     * Constructor of Event.
     *
     * @param taskName The name of the Event.
     * @param date The date and time when the Event will happen.
     * @throws DateTimeParseException If date cannot be casted to LocalDate.
     */
    public Event(String taskName, String date) throws DateTimeParseException {
        super(taskName);
        this.date = LocalDate.parse(date);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

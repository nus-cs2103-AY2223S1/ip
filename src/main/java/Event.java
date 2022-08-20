import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {
    protected LocalDate at;

    /**
     * Creates an event with a start time
     * @param description the description of the event
     * @param at the start time of the event
     */
    public Event(String description, String at) {
        super(description, "E");
        this.at = LocalDate.parse(at);
    }

    public String getAt() {
        return this.at.format(DateTimeFormatter.ISO_DATE);
    }
    /**
     * @return String representation of the event
     */
    @Override
    public String toString() {
        return "[" + super.getTaskType() + "]" + super.toString() + " (at: " + parseDate(at) + ")";
    }
}

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class that inherits from Task.
 */
public class Event extends Task {

    protected String datetime;
    protected LocalDate datetimeFormatted;

    /**
     * Constructor of Event class.
     *
     * @param description a String that describes the Event
     * @param datetime    a String that represents the datetime of the Event
     */
    public Event(String description, String datetime) {
        super(description);
        this.datetime = datetime;
    }

    /**
     * Constructor of Event class with formatted datetime as LocalDate.
     *
     * @param description a String that describes the Event
     * @param datetimeFormatted    a LocalDate that represents the datetime of the Event
     */
    public Event(String description, LocalDate datetimeFormatted) {
        super(description);
        this.datetimeFormatted = datetimeFormatted;
    }

    /**
     * Returns Event as a String formatted to be a line in file.
     *
     * @return String representing event.
     */
    @Override
    public String getFileFormat() {
        return String.format("E | %d | %s | %s", this.isDone ? 1 : 0, this.description, this.datetime);
    }

    /**
     * Returns a String that represents the Event.
     *
     * @return a String that represents the Event
     */
    @Override
    public String toString() {
        if (this.datetimeFormatted == null) {
            return "[E]" + super.toString() + " (at: " + this.datetime + ")";
        } else {
            String d = this.datetimeFormatted.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return "[E]" + super.toString() + " (at: " + d + ")";
        }
    }
}

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * An event task which contains a date/time for the event, which inherits from Task.
 */
public class Event extends Task {

    /** A string representing the date/time of the Event. */
    protected String at;

    /** A LocalDateTime representing the date and time of the Event */
    protected LocalDateTime atDateTime;


    /**
     * Constructor for an Event.
     *
     * @param description The description of the Event.
     * @param at          The date/time of the Event.
     */
    public Event(String description, String at) {
        super(description);
        try {
            atDateTime = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("d-MM-yyyy HH:m"));
        } catch (DateTimeParseException e) {
            try {
                atDateTime = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("yyyy-MM-d HH:m"));
            } catch (DateTimeParseException e1) {
                this.at = at;
            }
        }
    }

    /**
     * Returns the string representation of the Event object.
     *
     * @return The string representation of the Event object.
     */
    @Override
    public String toString() {
        String event = atDateTime != null ? atDateTime.format(DateTimeFormatter.ofPattern("d-MM-yyyy HH:mm")) : at;
        return "[E]" + super.toString() + " (at: " + event + ")";
    }

    /**
     * Returns the string representation of the Event object to be stored in the file.
     *
     * @return The string representation of the Event object to be stored in the file.
     */
    @Override
    public String toFile() {
        return "E | " + (this.isDone ? "1 | " : "0 | ") + this.description + " | " + this.at + "\n";
    }
}

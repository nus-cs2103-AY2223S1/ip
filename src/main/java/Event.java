import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An event is a task that starts at a specific time and ends at a specific time.
 */
public class Event extends Task {
    protected LocalDateTime eventDatetime;

    /**
     * Constructor for an event task.
     *
     * @param description Description of the event task
     * @param datetime The start/ end datetime of the event
     */
    public Event(String description, LocalDateTime datetime) {
        super(description);
        this.eventDatetime = datetime;
    }

    public Event(String description, LocalDateTime datetime, boolean completion) {
        super(description, completion);
        this.eventDatetime = datetime;
    }

    @Override
    public String toSaveFormat() {
        return String.format("E | %s | %s | %s", this.isDone ? "Y" : "N",
                this.description.replace("|", "\\|"),
                this.eventDatetime);
    }

    /**
     * Returns a string representation for the event task,
     * prefixed with a [E], followed by the event status, and
     * the event description.
     *
     * @return The string representation of the event task
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                this.eventDatetime.format(DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy HH:mm")));
    }
}

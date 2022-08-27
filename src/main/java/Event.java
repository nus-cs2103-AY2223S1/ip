import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * One of the task types, specifically those with
 * specified timing of when it's happening.
 */
public class Event extends Task {

    protected LocalDateTime at;

    /**
     * Constructor method.
     *
     * @param description The event description
     * @param at The time of the event
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * The stringtified version of this event.
     *
     * @return The print format of this event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                at.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
}
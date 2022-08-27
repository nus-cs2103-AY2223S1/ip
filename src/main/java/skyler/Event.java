package skyler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that starts at a specific time
 */
public class Event extends Task {

    protected LocalDateTime at;

    /**
     * Creates an event object
     *
     * @param description Description of the event.
     * @param at Start time of event.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Formats start time of event
     *
     * @param dateTime LocalDateTime object to be formatted.
     * @return Date and time in the format <code>MMM dd yyyy h:mma</code>.
     */
    public String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mma"));
    }

    @Override
    public String toStringUnformatted() {
        String unformatted = at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        return String.format("[D]%s (by: %s)", super.toString(), unformatted);
    }

    @Override
    public String toString() {
        String formatted = formatDateTime(at);
        return String.format("[E]%s (at: %s)", super.toString(), formatted);
    }
}

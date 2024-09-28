package jarvis.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event --- event task.
 */
public class Event extends Task {
    private final LocalDateTime startAt;

    /**
     * Constructor.
     *
     * @param description description of task.
     * @param at date of event.
     */
    public Event(String description, String at) {
        super(description);
        this.startAt = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("" + "[dd/MM/yyyy HHmm]"
                + "[MMM dd yyyy hh:mm a]"));
    }

    /**
     * Gets the date of event.
     *
     * @return date of event.
     */
    public String getStartAt() {
        return this.startAt.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.startAt.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")) + ")";
    }
}

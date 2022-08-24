import java.time.LocalDateTime;

/**
 * Represents an event, a type of task
 */
public class Event extends Task {
    private final LocalDateTime timing;

    /**
     * Constructs an event with some description and datetime string for the event's start
     * time
     *
     * @param description The specified description.
     * @param timing      The specified datetime string for the start time.
     */
    Event(String description, LocalDateTime timing) {
        super(description);
        this.timing = timing;
    }

    public String getTiming() {
        return Task.dateTimeDisplayFormatter.format(this.timing);
    }

    @Override
    public String toString() {
        return "[" + TaskType.E + "]" + super.toString() + " (at: " + this.getTiming() + ")";
    }
}

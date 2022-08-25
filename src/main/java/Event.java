import java.time.LocalDateTime;

/**
 * This class inherits from the abstract Task class
 * and encapsulates the logic of an Event task.
 */
public class Event extends Task {
    /* Duration field */
    private LocalDateTime duration;
    /**
     * Constructor for the Event Task.
     * @param description description of the task.
     */
    public Event(String description, LocalDateTime duration) {
        super(description);
        this.duration = duration;
    }

    /**
     * Override toString method for the Event Task.
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() +" (at: " + duration.format(OUTPUT_DATE_FORMAT) + ")";
    }

    /**
     * Override save format method from Task class.
     *
     * @return formatted String for the Event task.
     */
    @Override
    public String saveFormat() {
        return String.format("E | %s | %s", super.saveFormat(), this.duration);
    }
}

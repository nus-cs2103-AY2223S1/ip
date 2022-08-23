package duke.task;

/**
 * Event, a type of Task
 */
public class Event extends Task {
    private final String duration;

    /**
     * Constructor for Event object.
     * @param description Description of event.
     * @param duration Duration of event.
     */
    public Event(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    /**
     * Converts Event object into String form to save.
     * @return E | {status} | {description} | {duration}
     */
    @Override
    public String storedString() {
        return "E | " + super.storedString() + " | " + duration;
    }

    /**
     * Converts Event object into String form to display.
     * @return String representation of Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + duration + ")";
    }
}

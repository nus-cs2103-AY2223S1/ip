package duke.task;

/**
 * Event task.
 */
public class Event extends Task {
    private String at;

    /**
     * Constructor for Event.
     * @param description
     * @param at
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * String format of Deadline.
     * @return
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

    /**
     * Convert an Event to a String to store with Storage.
     * @return
     */
    @Override
    public String toMemoryString() {
        return "E | " + super.toMemoryString() + " | " + at;
    }
}

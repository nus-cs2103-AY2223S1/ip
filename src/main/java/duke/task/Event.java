package duke.task;

/**
 * Represents an event task.
 */
public class Event extends Task {

    protected String at;

    /**
     * Constructs an event instance.
     * @param description Description of task.
     * @param at Time range for task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getTiming() {
        return this.at;
    }

    /**
     * Returns string representation of event task.
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
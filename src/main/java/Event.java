/**
 * A subclass of the task class.
 */
public class Event extends Task {
    /**
     * The location or time of the event.
     */
    protected String at;

    /**
     * Constructor for an Event instance.
     * @param description The description of the event.
     * @param at The location or time of the event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Overrides the toString method in the parent class.
     * @return A string representing the current event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}

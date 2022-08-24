/**
 * Represents an event, a type of task
 */
public class Event extends Task {
    private final String at;

    /**
     * Constructs an event with some description and datetime string for the event's start
     * time
     *
     * @param description The specified description.
     * @param at          The specified datetime string for the start time.
     */
    Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[" + TaskType.E + "]" + super.toString() + " (at: " + this.at + ")";
    }
}

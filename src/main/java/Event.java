/**
 * Handles an event.
 */
public class Event extends Task {
    protected String datetime;

    /**
     * Creates an event.
     * @param description Description of event.
     * @param at Time of event.
     */
    public Event(String description, String at) {
        super(description);
        datetime = at;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), datetime);
    }
}

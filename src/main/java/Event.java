/**
 * Handles an event
 */
public class Event extends Task {
    protected String datetime;

    public Event(String description, String at) {
        super(description);
        datetime = at;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), datetime);
    }
}

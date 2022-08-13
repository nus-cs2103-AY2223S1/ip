/**
 * A task that start at a specific date/time and ends at a specific date/time.
 */
public class Event extends Task {
    private final String datetime;

    public Event(String description, String datetime) {
        super(description);
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), datetime);
    }
}

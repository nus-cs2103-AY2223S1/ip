public class Event extends Task {
    protected String eventTime;

    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), eventTime);
    }
}

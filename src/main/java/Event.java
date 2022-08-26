public class Event extends Task {
    private String eventTime;

    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    public Event(boolean isDone, String description, String eventTime) {
        super(isDone, description);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventTime + ")";
    }

    public String toDbString() {
        return "E" + " | " + super.toDbString() + " | " + eventTime;
    }
}

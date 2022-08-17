package tasks;

public class Event extends Task {

    private String eventDateTime;

    public Event(String description, String eventDateTime) {
        super(description);
        this.eventDateTime = eventDateTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.eventDateTime);
    }
}

public class Event extends Task {

    private String eventDateTime;

    Event(String description, String eventDateTime) {
        super(description);
        this.eventDateTime = eventDateTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (by: %s)", super.toString(), this.eventDateTime);
    }
}

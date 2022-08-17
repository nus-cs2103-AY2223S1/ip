public class Event extends Task {
    String details;

    Event(String description, String details) {
        super(description);
        this.details = details;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.details);
    }
}

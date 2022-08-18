public class Event extends Task {
    private String timing;

    public Event(String description, String deadline) {
        super(description);
        this.timing = deadline;
    }

    public String getTiming() {
        return timing;
    }

    @Override
    public String toString() {
        return String.format("[E] %s %s (at: %s)", getStatusIcon(), getDescription(), getTiming());
    }
}

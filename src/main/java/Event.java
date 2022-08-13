public class Event extends Task {

    private final String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[E]%s %s (at: %s)", super.getStatusIcon(), description, at);
    }
}

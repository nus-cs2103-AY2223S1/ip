public class Event extends Task {
    String at;
    Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.at);
    }
}

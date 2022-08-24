public class Event extends Task {
    private final String at;
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String stringify() {
        return String.format("D##%s##%s", super.stringify(), this.at);
    }
    @Override
    public String toString() {
        return String.format("[E]%s(at: %s)", super.toString(), this.at);
    }
}
public class Event extends Task {
    protected String at;

    Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String fileFormat() {
        return String.format("event | %s | %s | %b", super.description, at, super.isDone);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), at);
    }
}

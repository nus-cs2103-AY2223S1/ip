public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getWriteString() {
        return String.format("E | %s | %s", super.getWriteString(), this.at);
    }

    @Override
    public String toString() {
        return String.format("[E] %s (by: %s)", super.toString(), this.at);
    }
}
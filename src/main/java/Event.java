public class Event extends Todo {
    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (at: %s)", this.description, this.at);
    }
}

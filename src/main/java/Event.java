public class Event extends Task {
    // original access modifier was protected
    private final String start;

    public Event(String description, String start) {
        super(description);
        this.start = start;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + start + ")";
    }
}

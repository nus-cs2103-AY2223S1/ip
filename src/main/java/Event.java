public class Event extends Task {
    private final String timing;

    public Event(String task, String timing) {
        super(task, "event");
        this.timing = timing;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + timing + ")";
    }
}

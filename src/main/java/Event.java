public class Event extends Task {
    private final String period;

    public Event(String description, String period) {
        super(description);
        this.period = period;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.period + ")";
    }
}

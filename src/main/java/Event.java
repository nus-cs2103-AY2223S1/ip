public class Event extends Task {
    private final String timeRange;

    public Event(String name, String timeRange) {
        super(name);
        this.timeRange = timeRange;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (at %s)", super.toString(), this.timeRange);
    }
}
public class Event extends Task {
    private final String timeRange;

    public Event(String name, boolean initialComplete, String timeRange) {
        super(name, initialComplete);
        this.timeRange = timeRange;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (at %s)", super.toString(), this.timeRange);
    }

    @Override
    public String toFileRepresentation() {
        return "E" + "|" + (this.isComplete() ? "1" : "0") + "|" + this.getName() + "|" + this.timeRange;
    }
}
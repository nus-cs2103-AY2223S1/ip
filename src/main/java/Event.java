public class Event extends Task {
    protected String timeRange;

    Event(String desc, String timeRange) {
        super(desc);
        this.timeRange = timeRange;
    }

    Event(String desc, String timeRange, boolean isDone) {
        super(desc, isDone);
        this.timeRange = timeRange;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.timeRange);
    }

}

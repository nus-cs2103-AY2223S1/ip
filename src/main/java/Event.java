public class Event extends Task {
    protected String timeRange;

    public Event(String taskName, String timeRange) {
        super(taskName);
        this.timeRange = timeRange;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + timeRange + ")";
    }

}

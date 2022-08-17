public class Event extends Task {
    protected String timeRange;

    public Event(String taskDescription, String timeRange) {
        super(taskDescription);
        this.timeRange = timeRange;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + timeRange + ")";
    }

}

public class Event extends Task {
    protected String event;

    public Event(String taskName, String event) {
        super(taskName);
        this.event = event;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + event + ")";
    }
}

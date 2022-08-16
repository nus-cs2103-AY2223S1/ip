public class EventTask extends Task {
    protected String event;

    EventTask(String action, boolean isDone, String event) {
        super(action, isDone);
        this.event = event;
    }

    EventTask(String action, String event) {
        this(action, false, event);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + event + ")";
    }
}

public class EventTask extends Task {
    private final String eventTime;
    EventTask(String taskname, String eventTime) {
        super(taskname);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), eventTime);
    }
}

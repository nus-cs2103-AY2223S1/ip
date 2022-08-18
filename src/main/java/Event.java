public class Event extends Task {
    protected String description;
    protected boolean isDone;
    protected String eventTime;

    public Event (String description, String eventTime) {
        super(description);
        this.eventTime = eventTime.replaceFirst(" ", ": ");
    }

    @Override
    public String toString() {
        String s = String.format("[E]%s (%s)", super.toString(), this.eventTime);
        return s;
    }
}

public class Event extends Task{
    protected String eventTime;

    public Event(String description, String eventTime) {
        this.description = description;
        this.isDone = false;
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "[E] " + "[" + this.getStatusIcon() + "] " + this.description
                +  " (at: " + this.eventTime + ")";
    }
}

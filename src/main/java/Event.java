public class Event extends Task {
    public String eventTime;

    public Event(String name, String eventTime) {
        super(name);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + eventTime + ")";
    }
}

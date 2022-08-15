public class Event extends Task{
    String eventAt;

    public Event(String name, String eventAt) {
        super(name);
        this.eventAt = eventAt;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventAt + ")";
    }
}

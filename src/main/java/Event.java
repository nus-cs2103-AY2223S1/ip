public class Event extends Task{
    private final String eventTiming;
    public Event(String taskItem, String eventTiming) {
        super(taskItem);
        this.eventTiming = eventTiming;
    }

    @Override
    public String toString() {
        String eventTimingDisplay = String.format(" (at: %s)", this.eventTiming);
        return "[D]" + super.toString() + eventTimingDisplay;
    }
}

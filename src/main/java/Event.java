public class Event extends Task{
    private final EventDateTime eventTiming;
    public Event(String taskItem, EventDateTime eventTiming) {
        super(taskItem);
        this.eventTiming = eventTiming;
    }

    @Override
    public String toString() {
        String eventTimingDisplay = String.format(" (at: %s)", this.eventTiming);
        return "[E]" + super.toString() + eventTimingDisplay;
    }
}

public class Event extends Task {
    private String eventTimePeriod;

    public Event(String taskDescription, String eventTimePeriod) {
        super(taskDescription);
        this.eventTimePeriod = eventTimePeriod;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventTimePeriod + ")";
    }
}

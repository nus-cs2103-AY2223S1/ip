/**
 * The Event class represents a task
 * with a specific date or time.
 */
public class Event extends Task{
    private String eventTime;

    /**
     * Constructs a Event object
     * @param description description for the event.
     * @param eventTime string that represents time of event.
     */
    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    /**
     * Overriding method of toString() for Event.
     * @return the string representing Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + eventTime + ")";
    }
}

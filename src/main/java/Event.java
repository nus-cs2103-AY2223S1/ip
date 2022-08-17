/**
 * This class encapsulates Event tasks
 */
public class Event extends Task {
    private final String eventTime;

    /**
     * Constructor for Event class
     * @param taskDescription the content of the task
     * @param eventTime the time for the event
     */
    public Event(String taskDescription, String eventTime) {
        super(taskDescription);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " " + "(at: " + eventTime + ")";
    }
}

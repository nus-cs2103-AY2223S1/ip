/**
 * Encapsulates an Event task which occurs at a
 * specified time.
 *
 * @author Conrad
 */
public class Event extends Task {

    private String eventTime;

    /**
     * Constructor for creating an event task.
     *
     * @param taskDescription A description of the event.
     * @param eventTime The time at which the event takes place.
     */
    public Event(String taskDescription, String eventTime) {
        super(taskDescription);
        this.eventTime = eventTime;
    }

    /**
     * Return a string representation of an event task.
     *
     * @return The string representation of an event task.
     */
    @Override
    public String toString() {
        String taskStatusIndicator = "[E]" + (this.isCompleted() ? "[X] " : "[ ] ");
        return taskStatusIndicator + this.getTaskDescription() + " (at: " + this.eventTime + ")";
    }
}

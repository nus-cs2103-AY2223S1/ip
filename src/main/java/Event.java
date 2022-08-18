/**
 * Class used to represent an Event type task that has a start and end datetime.
 */
public class Event extends Task {
    protected String eventDate;

    public Event(String taskName, boolean isDone, String eventDate) {
        super(taskName, isDone);
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), eventDate);
    }
}

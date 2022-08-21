/**
 * Event Class that encapsulates details of an Event Task.
 *
 * @author Elgin Lee
 */
public class Event extends Task {
    /** The date and time of the event. */
    protected String date;

    /**
     * Constructor of Event.
     *
     * @param taskName The name of the Event.
     * @param date The date and time when the Event will happen.
     */
    public Event(String taskName, String date) {
        super(taskName);
        this.date = date;
    }

    /**
     * Constructor of Event.
     *
     * @param taskName The name of the event.
     * @param date The date and time when the Event will happen.
     * @param isDone True if the event is done, false otherwise.
     */
    public Event(String taskName, String date, boolean isDone) {
        super(taskName, isDone);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + this.date + ")";
    }
}

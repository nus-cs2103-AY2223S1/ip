/**
 * Event Class that encapsulates details of an Event Task.
 *
 * @author Elgin Lee
 */
public class Event extends Task {
    /** The date of the event. */
    protected String date;

    /** The timing of the event. */
    protected String time;

    /**
     * Constructor of Event.
     *
     * @param taskName The name of the Event.
     * @param date The date when the Event will happen.
     * @param time The time when the Event will happen.
     */
    public Event(String taskName, String date, String time) {
        super(taskName);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.date + " " + this.time + ")";
    }
}

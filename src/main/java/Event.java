/**
 * Event Task represents an event that takes place between certain dates/time.
 */
public class Event extends Task {
    protected String dateTime;

    /**
     * Constructor for an Event Task.
     *
     * @param description the description of the Event
     * @param dateTime the start and end dates/time of the Event
     */
    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        String symbol = this.isDone ? "X" : " ";
        return "[E][" + symbol + "] " + this.description + "(at:" + this.dateTime + ")\n";
    }
}

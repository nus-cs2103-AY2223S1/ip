/**
 * Event Task represents an event that takes place between certain dates/time.
 */
public class Event extends Task {
    protected TimeStamp start;
    protected TimeStamp end;

    /**
     * Constructor for an Event Task.
     *
     * @param description the description of the Event
     * @param dateTime the start and end dates/time of the Event
     */
    public Event(String description, String dateTime) throws DukeException {
        super(description);
        String[] duration = dateTime.split(" ~ ", 2);
        this.start = TimeStamp.of(duration[0]);
        this.end = TimeStamp.of(duration[1]);
    }

    @Override
    public String toString() {
        String symbol = this.isDone ? "X" : " ";
        return "[E][" + symbol + "] " + this.description + "(at:" + this.start + " -" + this.end + ")\n";
    }
}

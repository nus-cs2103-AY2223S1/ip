package duke;

/**
 *  Represents a <code>Event</code>. An <code>Event</code> is a <code>Task</code> with an <code>at</code>
 *  attribute which indicates where the event is located.
 */
public class Events extends Task {
    protected String location;

    /**
     * Initialises Event object. Sets location of Event.
     * @param taskName name of Event
     * @param location where Event is located
     */
    public Events(String taskName, String location) {
        super(taskName);
        this.location = location;
    }

    /**
     * Returns string representation of Event, including event name, location and done status.
     * @return String representation of Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + location + ")";
    }
}

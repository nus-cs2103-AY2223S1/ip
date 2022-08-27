package piggy.task;

/**
 * A Task made for events that can store the event's date.
 */
public class Event extends TaskWithDate {

    /**
     * Creates a new Event with the given description and datetime.
     * The Event is marked as not done by default.
     *
     * @param description The description of the event.
     * @param by The datetime of the event.
     */
    public Event(String description, String at) {
        super(description, at);
    }

    /**
     * Returns a String representation of the Event.
     *
     * @return A String of the format "[E][<X or ' '>] <description> (at: <datetime>)".
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + super.getDateTime() + ")";
    }
}


/**
 * An event task which contains a date/time for the event, which inherits from Task.
 */
public class Event extends Task{

    /** A string representing the date/time of the Event. */
    protected String at;

    /**
     * Constructor for an Event.
     * @param description The description of the Event.
     * @param at The date/time of the Event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the string representation of the Event object.
     * @return The string representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

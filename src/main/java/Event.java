/**
 * Event class that stores the Description and State of Event.
 *
 * CS2103T IP
 * AY22/23 Semester 1
 * @author Tan Jia Rong
 */
public class Event extends Task {
    /** Stores the timing of the event */
    protected String at;

    /**
     * Constructor for Event
     *
     * @param description Description of the Event
     * @param at The timing of the Event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the string representation of the Event.
     *
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

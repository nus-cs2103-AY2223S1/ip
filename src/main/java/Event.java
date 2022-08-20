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
     * Constructor for Event.
     *
     * @param description Description of the Event.
     * @param at The timing of the Event.
     */
    public Event(String description, String at) {
        super(description, "E");
        this.at = at;
    }

    /**
     * Constructor for Event.
     *
     * @param description Description of the Event.
     * @param done Completeness of Event.
     * @param at The timing of the Event.
     */
    public Event(String description, String done, String at) {
        super(description, done,"E");
        this.at = at;
    }

    /**
     * Gets timing of Event.
     *
     * @return Timing of Event.
     */
    public String getDate() {
        return this.at;
    }

    /**
     * Returns the string representation of the Event.
     *
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        return "[" + this.getType() + "]" + super.toString()  + " (at: " + at + ")";
    }
}

package duke;


/**
 * Adds on with an at field which allows users to set an event timing.
 *
 * @author Yuvaraj Kumaresan
 */
public class Event extends Task {

    /**
     * Time at which the event is at.
     */
    protected String at;

    /**
     * Constructor
     *
     * @param description String describing the event task.
     * @param at          String providing the timeframe for the event task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Converts event object to its string representation.
     *
     * @return String representation of the event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Gets the at attribute from the event object
     *
     * @return String representation of the at attribute.
     */
    public String getAt() {
        return this.at;
    }
}

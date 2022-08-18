/**
 * Child class Event
 *
 * Event a child class of Task has the same functionality
 * but adds on with an at field which allows users to set an event timing.
 *
 * @author Yuvaraj Kumaresan
 */
public class Event extends Task {

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
     * Method toString()
     *
     * @return String representation of the event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

/**
 * Events are tasks that have a description, a start and end time.
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructor for an Event task
     * @param description description of a task that the user inputs
     * @param at the time frame which this Event task occurs at
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Retrieves the time that an Event task occurs at
     * @return the time which the Event task occurs at
     */
    public String getTiming() {
        return this.at;
    }

    /**
     * toString method for an Event task
     * @return string representation of an Event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

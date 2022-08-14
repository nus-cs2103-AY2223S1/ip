/**
 * An Event object is a Task which has a start time and an end time.
 */
public class Event extends Task {
    private final String when;

    /**
     * Creates a new Event object with a given description, whether it has been done, the start
     * time and the end time.
     *
     * @param description the description of the event
     * @param isDone      whether the event is marked as done
     * @param when   the time of the event
     */
    public Event(String description, boolean isDone, String when) {
        super(description, isDone);
        this.when = when;
    }

    /**
     * Returns when the Event is scheduled to take place.
     *
     * @return the time range which the Event is scheduled to take table.
     */
    public String getStartTime() {
        return this.when;
    }


    /**
     * The task type code for an Event object is "E". Hence, this method returns "E".
     *
     * @return "E"
     */
    @Override
    public String getTaskTypeCode() {
        return "E";
    }

    /**
     * Returns the String representation of the Event object, i.e.
     *   a string in the format "[task type code][get status icon] description (at: time of event)".
     *
     * @return String representation of the Event object
     */
    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), this.when);
    }
}

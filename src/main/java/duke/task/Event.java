package duke.task;

/**
 * Encapsulation of an event.
 *
 * @author Sun Ruoxin
 */
public class Event extends Task {
    /** The time of the event. */
    protected String at;

    /**
     * Class constructor.
     *
     * @param description the description of the event
     * @param at the time of the event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Class constructor with specified status.
     *
     * @param description the description of the event
     * @param isDone the status of the event
     * @param at the time of the event
     */
    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Returns the type of the task represented by a character.
     *
     * @return "E" for event
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Returns the string representation of the event.
     *
     * @return the string representation of the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

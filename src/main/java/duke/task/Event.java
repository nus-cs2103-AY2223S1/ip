package duke.task;

/**
 * This class represents an event, {@code at} a location.
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructs an event with a description and a location.
     *
     * @param description the description of the event
     * @param at the location of the event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the description of the task, with an event tag.
     *
     * @return Description of the task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }
}

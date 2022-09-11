package duke.task;

/**
 * Represents a event task
 */
public class Event extends Task {

    protected String at;

    /**
     * Creates a new event task
     *
     * @param description the description of the event
     * @param at the place or time of the event
     */
    public Event(String description, String at){
        super(description);
        this.at = at;
    }

    /**
     * Returns a string representation of this event
     *
     * @return string representation
     */
    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

    /**
     * Returns a simplified string representation of this event
     *
     * @return simplified string representation
     */
    @Override
    public String toSimpleString() {
        return "E | " + super.toSimpleString() + " | " + this.at;
    }
}

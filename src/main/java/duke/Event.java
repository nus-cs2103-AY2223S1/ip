package duke;

/**
 * Represents an event task.
 */
public class Event extends Task {

    protected String at;

    /**
     * Creates a new event task with the given description and period.
     *
     * @param description Description of the task.
     * @param at          Period in any format.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

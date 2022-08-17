/**
 * A task with a date and time
 */
public class Event extends Task {

    /** The date and time */
    protected String at;

    /**
     * Constructs a new Event with the given description and date and time
     *
     * @param description The task description
     * @param at The date and time of the task
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    protected String getTypeSymbol() {
        return "E";
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + at + ")";
    }
}

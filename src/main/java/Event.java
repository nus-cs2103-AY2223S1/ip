/**
 * Tasks with a date/time of occurrence
 */
public class Event extends Task {

    /** The date/time of the task */
    protected String at;

    /**
     * Constructs a new Event with the given description and date and time of occurrence
     *
     * @param description The task description
     * @param at The date/time of the task
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

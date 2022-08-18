public class Event extends Task {
    protected String at;

    /**
     * Constructor for the event type of task.
     *
     * @param description description of the event task
     * @param at the event timing of the event task
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * toString method of an event task.
     *
     * @return the string representation of an event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

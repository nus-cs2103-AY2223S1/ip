public class Event extends Task {
    protected String at;

    /**
     * Constructor for Event
     * @param description Description of the Event
     * @param at The Timing of the Event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * To string method of Event
     *
     * @return String Representation of an Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

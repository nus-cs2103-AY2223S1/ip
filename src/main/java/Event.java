public class Event extends Task {
    protected String at;

    /**
     * A constructor that creates an instance of Event.
     *
     * It takes in a description of the event and the time of the event.
     *
     * @param description The description of the event.
     * @param at The time of the event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }
}

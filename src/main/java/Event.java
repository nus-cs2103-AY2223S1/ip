public class Event extends Task {
    protected String at;

    /**
     * Constructor for Event class.
     * @param description description for event
     * @param at duration of event
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

public class Event extends Task {
    /**
     * A string the describes the date/time of the event.
     */
    private String at;

    /**
     * Constructor for a deadline.
     * @param description the description of the deadline
     * @param at the date/time of the event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns a string representation of the event.
     * @return a string representing the event
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.at);
    }
}

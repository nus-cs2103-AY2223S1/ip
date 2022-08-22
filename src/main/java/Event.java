public class Event extends Task {
    protected String at;
    /**
     * Constructor for a event instance.
     *
     * @param description the description of the event
     * @param at the time of the event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * String representation of the event.
     *
     * @return String representing this event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

    /**
     * Gets the string representation of this task for storage in a file.
     *
     * @return a String containing the task name, description, whether it is
     *         completed, and the due date.
     */
    public String encode() {
        return String.format("%s # %s # %s",
                "E",
                super.encode(),
                this.at);
    }
}

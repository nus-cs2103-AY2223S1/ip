public class Event extends Task {
    protected String at;

    /**
     * Constructs an Event task.
     *
     * @param description Description of the Event task.
     * @param at Date the Event task occurs at.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Shows the Event task description and the date it occurs at.
     *
     * @return String with the Event task description and the date it occurs at.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

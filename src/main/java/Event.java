/**
 * The Event class represents a task that
 * starts at a specific time and
 * ends at a specific time.
 */
public class Event extends Task {
    private String at;

    /**
     * Constructs a new Event task with a specified
     * description, start time and end time.
     *
     * @param description A string specifying the description of the event.
     * @param at A string specifying the time at which the event occurs.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the string representation of an event.
     *
     * @return The string representing the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

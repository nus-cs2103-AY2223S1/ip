/**
 * The Event class.
 */
public class Event extends Task {
    /** Events have a time that it takes place that is a String. */
    protected String at;

    /**
     * Constructor for Event objects.
     * @param description The description of the event task.
     * @param at The time of the event.
     * @throws DukeException For Duke related exceptions.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        this.at = at;
        if (description.isBlank() || at.isBlank()) {
            throw new DukeException("The description of event task is missing information!");
        }
    }

    /**
     * String representation of Event task object.
     * @return Returns the String representation of the current object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}

/**
 * Represents a Event task.
 *
 * @author WR3nd3
 */
public class Event extends Task {

    protected String at;
    protected String id = "[E]";

    /**
     * Constructor for the Event task.
     *
     * @param description String representing the description of the event.
     * @param at String representing the time of the event.
     * @param isCompleted Boolean representing whether the task is completed.
     */
    public Event(String description, String at, Boolean isCompleted) {
        super(description, isCompleted);
        this.at = at;
    }

    /**
     * Returns a string representation of the event.
     *
     * @return a string consisting of the event completion status and description.
     */
    @Override
    public String toString() {
        return id + super.toString() + " (at: " + at + ")";
    }
}
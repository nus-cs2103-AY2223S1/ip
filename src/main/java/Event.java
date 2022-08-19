/**
 * Class to represent tasks of the type Event.
 *
 * @author Melissa Anastasia Harijanto
 */
public class Event extends Task {
    /** String that represents the venue of the task. */
    protected String at;

    /** Constructor for the Event class. */
    public Event(String taskName, String at) {
        super(taskName);
        this.at = at;
    }

    /**
     * Returns the String representation of the Event task.
     *
     * @return the String representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
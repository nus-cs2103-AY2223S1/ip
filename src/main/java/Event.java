/**
 * A task that starts at a specific time and ends at a specific time.
 *
 * @author Lai Han Wen
 */
public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the String representation of an event task.
     *
     * @return the String representation of an event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

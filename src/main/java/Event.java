/**
 * Encapsulate Event which is-a Task
 *
 * @author: Jonas Png
 */
public class Event extends Task {

    protected String timing;

    /**
     * Class constructor for Event
     */
    public Event(String description, String timing) {
        super(description);
        this.timing = timing;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.timing + ")";
    }

}

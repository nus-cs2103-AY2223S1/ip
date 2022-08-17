/**
 * The EventTask is a Task that has at event time.
 */
public class EventTask extends Task {

    protected String at;

    /**
     * Constructor of a EventTask object
     * @param description      Name of the task
     * @param at               Date of the event.
     */
    public EventTask(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

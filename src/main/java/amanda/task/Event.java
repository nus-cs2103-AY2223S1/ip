package amanda.task;

/**
 * Event is a task associated with a the starting time of the event.
 */
public class Event extends Task {

    protected String by;

    /**
     * Constructor of the Event class.
     * @param description the content of the event.
     * @param by the starting time of the event.
     */
    public Event(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Get the starting time of the event.
     * @return the starting time of the event.
     */
    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + by + ")";
    }
}

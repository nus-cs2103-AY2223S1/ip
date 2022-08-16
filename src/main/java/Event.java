public class Event extends Task {
    /* Duration of Event */
    protected String duration;

    /**
     * Constructor for Event Class.
     * @param name String representation of task name.
     * @param duration String representation of duration of event.
     */
    public Event(String name, String duration) {
        super(name);
        this.duration = duration;
    }
    /**
     * Returns string representation of Event object.
     * @return String representation of Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + duration + ")";
    }
}

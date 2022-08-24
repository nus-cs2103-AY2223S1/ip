public class Event extends Task {
    /* Duration of Event */
    protected String duration;

    /**
     * Constructor for Event Class.
     * @param name String representation of task name.
     * @param duration String representation of duration of event.
     */
    public Event(String name, String duration) {
        this.name = name;
        this.isDone = false;
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

    @Override
    public String saveFormat() {
        int status = isDone ? 1 : 0;
        return String.format("%s | %d | %s | %s", "E", status, name, duration);
    }
}

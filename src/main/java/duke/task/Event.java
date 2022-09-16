package duke.task;

/**
 * Task of type event.
 */
public class Event extends Task {
    protected String time;

    /**
     * Constructor of an event task.
     *
     * @param description description of event.
     * @param time string representing time of event.
     */
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public String getOutput() {
        return String.format("E | %d | %s | %s | %s", getIsDone() ? 1 : 0, getDescription(), time, this.tag);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ") # " + this.tag;
    }
}

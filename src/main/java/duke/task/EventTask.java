package duke.task;

/**
 * Represents an event task.
 */
public class EventTask extends Task {
    protected String at;

    public EventTask(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

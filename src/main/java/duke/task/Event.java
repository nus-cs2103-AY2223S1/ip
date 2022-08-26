package duke.task;

/**
 * Event class
 */
public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.type = Type.EVENT;
        this.at = at;
    }

    @Override
    public String toDataString() {
        return "E" + super.toDataString() + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

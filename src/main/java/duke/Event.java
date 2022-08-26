package duke;

/**
 * Event class extended from Task class.
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructor for Event class.
     *
     * @param description Description of the event.
     * @param at Date of the event in form of String.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String saveString() {
        return "E | " + (this.isDone ? "1 | " : "0 | ") + this.description + " | " + this.at;
    }
}

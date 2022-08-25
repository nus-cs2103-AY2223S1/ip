package duke;

/**
 * An Event class which is a subclass of Task
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructor for Event class
     * @param description Description of the event
     * @param at Date of the event in String format
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Creates a String to represent the event task during listing
     * @return String to be displayed when list
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Creates a String to be saved in the file
     * @return String to be displayed in the file
     */
    @Override
    public String savedString() {
        return "E | " + (this.isDone ? "1 | " : "0 | ") + this.description + " | " + this.at;
    }
}
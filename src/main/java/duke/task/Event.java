package duke.task;

/**
 * The Event class represents a Task that occurs a given date.
 *
 * @author Edric Yeo
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructor for a Event instance with a given date.
     *
     * @param description The description of the Event.
     * @param at          The date that the Event occurs.
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
    public String toDataEntry() {
        int mark = this.isDone ? 1 : 0;
        return String.format("E # %d # %s # %s\n", mark, this.description, this.at);
    }
}

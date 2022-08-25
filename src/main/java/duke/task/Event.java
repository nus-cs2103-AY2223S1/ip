package duke.task;
/**
 * Represents an event task with description and date/time the event occurs at.
 */
public class Event extends Task {

    protected String at;

    /**
     * Creates a event task object.
     * @param description details of task.
     * @param at date/time of when task occurs.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + at + ")";
    }

    @Override
    public String toStringForStorage() {
        return "E|" + super.toStringForStorage() + "|" + at;
    }
}

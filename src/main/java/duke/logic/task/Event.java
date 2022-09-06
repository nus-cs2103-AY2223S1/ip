package duke.logic.task;

/**
 * Represents a event task.
 */
public class Event extends Task {

    protected String at;
    /**
     * Constructor for event.
     *
     * @param detail String
     */
    public Event(String detail, String at) {
        super(detail);
        this.at = at;
    }

    /**
     * Constructor for event.
     * @param detail
     * @param isDone
     * @param at
     */
    public Event(String detail, boolean isDone, String at) {
        super(detail, isDone);
        this.at = at;
    }

    /**
     * Returns String form of the task
     * @return String
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Returns String to be stored in the hardware list.
     * @return String
     */
    @Override
    public String storedData() {
        return "E" + "|" + super.storedData() + "|" + at;
    }
}

/**
 * Represents an event, a type of task
 */
public class Event extends Task {
    private final String at;

    /**
     * Constructs an event with some description and datetime string for the event's start
     * time
     *
     * @param description The specified description.
     * @param isDone      The boolean indicating whether the task is done.
     * @param at          The specified datetime string for the start time.
     */
    Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
        this.taskType = TaskType.E;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Event has an additional datetime field for at
     */
    @Override
    public String encode(String delimiter) {
        return super.encode(delimiter) + delimiter + this.at;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + this.at + ")";
    }
}

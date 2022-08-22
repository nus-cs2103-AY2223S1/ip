/**
 * Represents a time-sensitive task that has a time period
 * during which it will occur.
 */
public class EventTask extends Task{

    /** The period during which the event will take place. */
    private String period = "";

    /**
     * Constructor for an event task.
     * @param description A description of the event
     * @param period The time period for the event
     */
    public EventTask(String description, String period) {
        super(description, TaskType.EVENT);
        this.period = period;
    }

    public EventTask(String description, String period, boolean isMarked) {
        super(description, TaskType.EVENT, isMarked);
        this.period = period;
    }

    /**
     * Returns tthe string representation of the event.
     * @return String representation of the event
     */
    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), this.period);
    }

    @Override
    public String encodeForStorage() {
        return String.format("%s|%s", super.encodeForStorage(), this.period);
    }
}

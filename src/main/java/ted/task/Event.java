package ted.task;

/**
 * Represents an event. An <code>Event</code> object corresponds to an event description
 * and event date given by the user.
 */
public class Event extends Task {
    private String eventTimePeriod;

    /**
     * Creates Event object with default not done status.
     *
     * @param taskDescription event name.
     * @param eventTimePeriod event time period.
     */
    public Event(String taskDescription, String eventTimePeriod) {
        super(taskDescription);
        this.eventTimePeriod = eventTimePeriod;
    }

    /**
     * Creates Event object that is specified to be done or not done.
     *
     * @param taskDescription event name.
     * @param isTaskDone boolean indicating task's done status.
     * @param eventTimePeriod event time period.
     */
    public Event(String taskDescription, boolean isTaskDone, String eventTimePeriod) {
        super(taskDescription, isTaskDone);
        this.eventTimePeriod = eventTimePeriod;
    }

    /**
     * Returns Event in the correct String format for bot response.
     *
     * @return String that represents Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.eventTimePeriod + ")";
    }

    /**
     * Returns Event in the correct String format to write to file.
     *
     * @return String that represents Event.
     */
    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | " + this.eventTimePeriod + "\n";
    }
}

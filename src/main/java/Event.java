/**
 * An Event object is a Task which has a start time and an end time.
 */
public class Event extends Task {
    private final String startTime;
    private final String endTime;

    /**
     * Creates a new Event object with a given description, whether it has been done, the start
     * time and the end time.
     *
     * @param description the description of the event
     * @param isDone      whether the event is marked as done
     * @param startTime   the start time of the event
     * @prarm endTime     the end time of the event
     */
    public Event(String description, boolean isDone, String startTime, String endTime) {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns start time of the Event.
     *
     * @return the start time of the Event
     */
    public String getStartTime() {
        return this.startTime;
    }

    /**
     * Returns end time of the Event
     *
     * @return end time of the Event
     */
    public String getEndTime() {
        return this.endTime;
    }

    /**
     * The task type code for an Event object is "E". Hence, this method returns "E".
     *
     * @return "E"
     */
    @Override
    public String getTaskTypeCode() {
        return "E";
    }
}

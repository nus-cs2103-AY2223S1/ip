public class Event extends Task {
    private String eventTime;
    /**
     * Constructor to create a new Event
     *
     * @param task  the task that you want to complete (String)
     * @param eventTime  the time range for this event (String)
     */
    public Event(String task, String eventTime) {
        this.task = task;
        this.eventTime = eventTime;
    }

    /**
     * To String method that returns the task in string form to the user
     *
     * @return  the task in string format
     */
    @Override
    public String toString() {
        return "[E] " + this + " (at: " + this.eventTime + ")";
    }
}

/**
 * The Event class represents a task
 * with a specific date or time.
 */
public class Event extends Task{
    private String eventTime;

    /**
     * Constructs a Event object
     * @param description description for the event.
     * @param eventTime string that represents time of event.
     */
    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    public Event(int i, String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
        if (i == 1) {
            this.markDone();
        }
    }

    @Override
    public String toStore() {
        String status = super.isDone ? "1" : "0";
        String temp = "E" + " | " + status + " | " + super.description + " | " + this.eventTime;
        return temp;
    }


    /**
     * Overriding method of toString() for Event.
     * @return the string representing Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + eventTime + ")";
    }
}

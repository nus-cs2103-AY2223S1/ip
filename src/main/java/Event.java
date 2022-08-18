/**
 * Event class inherits from Task
 * Event objects are tasks with specific start and end time
 * @author Nam Minh Quan
 */

public class Event extends Task {
    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * Sets a new start and end time
     * @param time new time for Event
     */
    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + this.time + ")";
    }
}

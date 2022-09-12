package sally.task;

/**
 * Event class to represent new Event task
 *
 * @author liviamil
 */

public class Event extends Task {
    protected String moreInfo;

    public Event(String description, String moreInfo) {
        super(description);
        this.moreInfo = moreInfo;
    }

    public String getOutput() {
        return String.format("E | %d | %s | %s", isDone ? 1 : 0, description, moreInfo);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.moreInfo + ")";
    }
}

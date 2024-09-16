package sally.task;

/**
 * Event class to represent new Event task
 *
 * @author liviamil
 */

public class Event extends Task {
    protected String moreInfo;

    /**
     * Constructor for Event class
     *
     * @param description description of Event task
     * @param moreInfo venue for Event task in String
     */
    public Event(String description, String moreInfo) {
        super(description);
        this.moreInfo = moreInfo;
    }

    /**
     * Gets the output string for save to file
     *
     * @return output string for save to file
     */
    public String getOutput() {
        return String.format("E | %d | %s | %s", isDone ? 1 : 0, description, moreInfo);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.moreInfo + ")";
    }
}

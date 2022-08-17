/**
 * Deadline Task represents an action that needs to be done by a certain date or time.
 */
public class Deadline extends Task {
    protected String dateTime;

    /**
     * Constructor for a Deadline Task.
     *
     * @param description the description of the Task
     * @param dateTime the deadline of the Task
     */
    public Deadline(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        String symbol = this.isDone ? "X" : " ";
        return "[D][" + symbol + "] " + this.description + "(by:" + this.dateTime + ")\n";
    }
}

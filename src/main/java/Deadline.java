/**
 * Deadline Task represents an action that needs to be done by a certain date or time.
 */
public class Deadline extends Task {
    protected TimeStamp dateTime;

    /**
     * Constructor for a Deadline Task.
     *
     * @param description the description of the Task
     * @param dateTime the deadline of the Task
     */
    public Deadline(String description, String dateTime) throws DukeException {
        super(description);
        this.dateTime = TimeStamp.of(dateTime);
    }

    @Override
    public String toString() {
        String symbol = this.isDone ? "X" : " ";
        return "[D][" + symbol + "] " + this.description + "(by:" + this.dateTime + ")\n";
    }
}

/** Event class to represent an event.
 * @author Silas Tay A0233425M
 */
public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns String representation of Event
     * @return String representation of Event
     */
    @Override
    public String toString() {
        String completionString = this.isDone ? "[E][x]" : "[E][ ]";
        return completionString + " " + this.description + " (at: " + this.at + ")";
    }
}

/**
 * The Event class contains information of an Event task.
 * @author Sheryl-Lynn Tan (G11)
 */
public class Event extends Task {
    protected String at;
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E] " + this.getStatusIcon() + " " + this.description + " (at: " + at + ")";
    }

    @Override
    public String toFileOutput() {
        return "Event~" + this.description + "~" + this.at;
    }
}
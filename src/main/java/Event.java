/**
 * The Event class contains information of an Event task.
 * @author Sheryl-Lynn Tan (G11)
 */
public class Event extends Task {
    /**
     * Specified time  of an Event.
     */
    protected String at;

    /**
     * Constructor for Event.
     * @param description of task name.
     * @parameter at date of event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * This method returns the string of an Event.
     */
    @Override
    public String toString() {
        return "[E] " + this.getStatusIcon() + " " + this.description + "(at: " + at + ")";
    }

}
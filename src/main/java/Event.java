/**
 * A task to be attended at a certain time.
 * CS2103T iP
 * AY22/23 Semester 1
 *
 * @author Perry Wong
 */
public class Event extends Task {

    /**
     * Time at which the Event is attended.
     */
    protected String at;

    /**
     * A basic constructor to instantiate the Event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Method that returns the description of the Event.
     *
     * @return The description of the Event along with its status.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

package wagwan;

/**
* Event is a Task with a description and a date/time.
*
* @author Linus Chui
*/
public class Event extends Task {

    protected String event;

    /**
     * A constructor for a event.
     *
     * @param description the description the event.
     * @param event the date/time of the event.
     */
    public Event(String description, String event) {
        super(description);
        this.event = event;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.event + ")";
    }
}

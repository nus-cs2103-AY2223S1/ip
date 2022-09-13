package Duke;

/**
 * This is the event class that is being called
 * by users
 */
public class Event extends Task{
    protected String at;

    /**
     * Constructor for the class Event
     * @param description   Description of the event
     * @param at            Time of the event
     */
    public Event(String description, String at) {
        super(description);
        assert description != "": "Description should not be empty";
        this.at = at;
    }
    @Override
    public String toString() {

        return "[E]" + super.toString() + "(at: " + at + ")";
    }
}
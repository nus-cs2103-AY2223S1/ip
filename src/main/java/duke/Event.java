package duke;

/**
 * A subclass of Task that focuses on processing Event inputs
 */
public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * toString method that turns the input of event into a String type
     *
     * @return String to process Event input
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }
}

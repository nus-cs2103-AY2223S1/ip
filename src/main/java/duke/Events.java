package duke;

/**
 * A task class to indicate an event hence,
 * it requires a description of said event
 * and timing for that event
 */
public class Events extends Task {
    protected String at;
    public Events(String description,String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns string representation of an event
     * with its timing
     * @return String representation
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

}
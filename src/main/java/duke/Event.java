package duke;

/**
 * Encapsulates a Task which has a duration.
 *
 */
public class Event extends Task {
    private String duration;

    /**
     * Constructor.
     *
     * @param name
     * @param duration
     */
    public Event(String name, String duration) {
        super(name);
        this.duration = duration;
    }

    /**
     * Returns String representation of the Event object.
     *
     * @return String representation of the Event object
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.duration + ")";
    }

    /**
     * Returns the duration of the Event object as a String.
     *
     * @return duration of the Event object
     */
    public String getDuration() {
        return this.duration;
    }
}

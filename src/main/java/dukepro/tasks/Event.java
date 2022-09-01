package dukepro.tasks;

/**
 * Class for Event.
 */
public class Event extends Task {
    protected String at;

    /**
     * Creates an Event.
     *
     * @param description the description of each event.
     * @param at the location of the event.
     * @return An Event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns String format of this class.
     *
     * @return A String.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Returns String format of this class to be
     * saved in the tasklist.txt file.
     *
     * @return A String.
     */
    @Override
    public String fileForm() {
        return "E" + "," + super.fileForm() + "," + this.at;
    }
}

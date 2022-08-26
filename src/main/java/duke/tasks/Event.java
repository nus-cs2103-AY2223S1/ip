package duke.tasks;

/**
 * Tasks that occur at a certain time
 */
public class Event extends Task {

    /** The timing of occurrence */
    private String at;

    /**
     * Constructs a new Event with the given description and timing of occurrence
     *
     * @param description The task description
     * @param at The timing of occurrence
     */
    public Event(String description, String at) {
        super(description, 'E');
        this.at = at;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + at + ")";
    }
}

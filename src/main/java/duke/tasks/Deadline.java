package duke.tasks;

/**
 * Tasks with a deadline
 */
public class Deadline extends Task {

    /** The deadline */
    private String by;

    /**
     * Constructs a new Deadline with the given description and deadline 
     *
     * @param description The task description
     * @param by The task deadline
     */
    public Deadline(String description, String by) {
        super(description, 'D');
        this.by = by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}

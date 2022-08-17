/**
 * A task with a deadline
 */
public class Deadline extends Task {

    /** The deadline */
    protected String by;

    /**
     * Constructs a new Deadline with the given description and deadline 
     *
     * @param description The task description
     * @param by The task deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    protected String getTypeSymbol() {
        return "D";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}

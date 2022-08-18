public class Deadline extends Task {
    /**
     * The due date or time of the Deadline instance.
     */
    protected String by;

    /**
     * Constructor for a Deadline instance.
     * @param description The description of the Deadline instance.
     * @param by The due date or time of the Deadline instance.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Overrides the toString method in the parent class.
     * @return A string representing the current deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}

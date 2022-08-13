/**
 * Deadline class that inherits from Task.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructor of Deadline class.
     *
     * @param description the description of the task
     * @param by          a String that represents date of deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a String that represents the Deadline.
     *
     * @return a String that represents the Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

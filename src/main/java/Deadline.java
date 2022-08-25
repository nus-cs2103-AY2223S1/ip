/**
 * A deadline task which contains a date/time of the deadline, which inherits from Task.
 */
public class Deadline extends Task{

    /** A string representing the due date/time of the Deadline. */
    protected String by;

    /**
     * Constructor for a Deadline.
     * @param description The description of the Deadline.
     * @param by The due date/time of the Deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the Deadline object.
     * @return The string representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

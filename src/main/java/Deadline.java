/**
 * Represents a Deadline task.
 *
 * @author WR3nd3
 */
public class Deadline extends Task {

    protected String by;
    protected String id = "[D]";

    /**
     * Constructor for the deadline task.
     *
     * @param description String representing the description of the deadline.
     * @param by String representing the time of the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline.
     *
     * @return a string consisting of the deadline completion status and description.
     */
    @Override
    public String toString() {
        return id + super.toString() + " (by: " + by + ")";
    }
}
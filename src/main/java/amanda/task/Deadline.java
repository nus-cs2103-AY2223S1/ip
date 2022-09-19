package amanda.task;

/**
 * Deadline is a task that has a deadline.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructor for Deadline class.
     * @param description the content of the deadline.
     * @param by the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Get the deadline.
     * @return the deadline.
     */
    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }
}

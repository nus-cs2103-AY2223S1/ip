/**
 * A deadline is a task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {
    protected String dueDate;

    /**
     * Constructor for a deadline task.
     *
     * @param description Description of the deadline task
     * @param dueDate The due date of the deadlined task
     */
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Returns a string representation for the deadline task,
     * prefixed with a [D], followed by the deadline status, and
     * the deadline description.
     *
     * @return The string representation of the deadline task
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.dueDate);
    }
}

/**
 * Represents a deadline, a type of task
 */
public class Deadline extends Task {
    private final String by;

    /**
     * Constructs a deadline with some description and a datetime string to indicate
     * the deadline
     *
     * @param description The specified description.
     * @param by          The specified datetime string for the deadline.
     */
    Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[" + TaskType.D + "]" + super.toString() + " (by: " + this.by + ")";
    }
}

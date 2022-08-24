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
     * @param isDone      The boolean indicating whether the task is done.
     * @param by          The specified datetime string for the deadline.
     */
    Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
        this.taskType = TaskType.D;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Deadline has an additional datetime field for by
     */
    @Override
    public String encode(String delimiter) {
        return super.encode(delimiter) + delimiter + this.by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.by + ")";
    }
}

package task;

/**
 * Class representing a Deadline task
 */
public class Deadline extends Task {
    private String by;

    /**
     * Creates new Deadline task
     * @param description Description for the deadline
     * @param by When to finish the task by
     */
    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }
    @Override
    protected String getTypeIndicator() {
        return "D";
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), by);
    }
}

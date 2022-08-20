public class Deadline extends Task {
    protected String by;

    /**
     * Creates a task with a deadline
     * @param description the description of the task
     * @param by the deadline of the task
     */
    public Deadline(String description, String by) {
        super(description, "D");
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }

    /**
     * @return String representation of the deadline task
     */
    @Override
    public String toString() {
        return "[" + super.getTaskType() + "]" + super.toString() + " (by: " + by + ")";
    }
}

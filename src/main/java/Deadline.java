public class Deadline extends Task {
    protected String by;

    /**
     * A constructor that creates an instance of Deadline.
     *
     * It takes in a description of the task and the deadline of the task.
     *
     * @param description The description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}

public class Deadline extends Task {
    protected String by;

    /**
     * The constructor for the Deadline
     * @param description the description of the task
     * @param by the deadline of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * The string representation of the deadline
     * @return a string representing the description and the due date of the deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

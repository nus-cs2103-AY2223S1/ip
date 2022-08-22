public class Deadline extends Task {
    protected String by;
    /**
     * Constructor for a deadline instance.
     *
     * @param description the description of the task
     * @param by the due date of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * String representation of the deadline.
     *
     * @return String representing this task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Gets the string representation of this task for storage in a file.
     *
     * @return a String containing the task name, description, whether it is
     *         completed, and the due date.
     */
    public String encode() {
        return String.format("%s # %s # %s",
                "D",
                super.encode(),
                this.by);
    }
}

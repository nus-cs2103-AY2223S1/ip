public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for the deadline type of task.
     *
     * @param description description of the deadline task
     * @param by the deadline timing of the deadline task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * toString method of a deadline task.
     *
     * @return the string representation of a deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

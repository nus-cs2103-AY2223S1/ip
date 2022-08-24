public class Deadline extends Task {
    /**
     * A string the describes the due date/time of the deadline.
     */
    private String dueBy;

    /**
     * Constructor for a deadline.
     * @param description the description of the deadline
     * @param dueBy the due date/time of the deadline
     */
    public Deadline(String description, String dueBy) {
        super(description);
        this.dueBy = dueBy;
    }

    /**
     * Returns a string used to save the task.
     * @return a string used to save the task
     */
    @Override
    public String saveString() {
        return String.format("D | %s | %s", super.saveString(), this.dueBy);
    }

    /**
     * Returns a string representation of thew deadline.
     * @return a string representing the deadline
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.dueBy);
    }
}

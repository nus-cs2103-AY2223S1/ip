/**
 * Deadlines are tasks that have a description and must be done by a certain time.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for Deadline task
     * @param description description of a task that the user inputs
     * @param by the cut off time for user to complete the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Retrieves the cut off time of a deadline task
     * @return the cut off time of a task
     */
    public String getCutOff() {
        return this.by;
    }

    /**
     * toString method for a Deadline task
     * @return string representation of a Deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

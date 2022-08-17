/**
 * The Deadline class represents a task
 * that needs to be done before a specific date/time.
 */
public class Deadline extends Task {
    private String by;

    /**
     * Constructs a new Deadline task with a specified
     * description and time.
     *
     * @param description A string specifying the description of the task.
     * @param by The specified deadline by which the task must be completed.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of a Deadline task.
     *
     * @return The string representing the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

/**
 * The Deadline class represents a task
 * that needs to be done by a specified date/time.
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

    /**
     * Returns the representation of a Deadline task when stored in a data file on the hard disk.
     *
     * @return a string representing the Deadline task as it is stored on a data file on the hard disk.
     */
    @Override
    public String toData() {
        return "D | " + super.toData() + " | " + by + "\n";
    }
}

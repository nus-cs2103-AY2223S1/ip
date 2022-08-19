/**
 * Class to represent tasks of the type Deadline.
 *
 * @author Melissa Anastasia Harijanto
 */
public class Deadline extends Task {
    /** String that represents when a task must be done by. */
    protected String by;

    /**
     * Constructor for the Deadline class.
     *
     * @param taskName The name of the task.
     * @param by The time it has to be done by.
     */
    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
    }

    /**
     * Returns the String representation of the Deadline task.
     *
     * @return the String representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
    
}

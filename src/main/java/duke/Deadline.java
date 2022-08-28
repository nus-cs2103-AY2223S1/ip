package duke;

/**
 * Class which encapsulates a task which has a deadline.
 *
 */
public class Deadline extends Task {
    private String deadline;

    /**
     * Constructor.
     *
     * @param name
     * @param deadline
     */
    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Returns String representation of Deadline object.
     *
     * @return String representation of Deadline object
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }

    /**
     * Returns the deadline of the Deadline object.
     * @return the deadline in String format
     */
    public String getDeadline() {
        return this.deadline;
    }
}

/**
 * The Deadline class extends the Task class as it is a more specific type of task.
 */
public class Deadline extends Task {

    private String deadline;

    /**
     * Public constructor for a Deadline.
     *
     * @param name name/description of the task.
     * @param deadline when the task is due.
     */
    public Deadline(String name, String deadline) {
        super(name.substring(9));
        this.deadline = deadline;
    }

    /**
     * Overrides the toString() method in the Task class, represents a Deadline by adding a "[D]" in front of the
     * general Task representation.
     *
     * @return String representation of a Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + this.deadline + ")";
    }
}

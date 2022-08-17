/**
 * The Deadline class contains information of a Deadline task.
 * @author Sheryl-Lynn Tan (G11)
 */
public class Deadline extends Task {
    /**
     * Specified deadline time of a Deadline.
     */
    protected String by;

    /**
     * Constructor for Deadline.
     * @param description of task name.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * This method returns the string of a Deadline.
     */
    @Override
    public String toString() {
        return "[D] " + this.getStatusIcon() + " " + this.description + "(by: " + by + ")";
    }

}
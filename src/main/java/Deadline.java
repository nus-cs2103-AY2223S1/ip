/**
 * The Deadline class contains information of a Deadline task.
 * @author Sheryl-Lynn Tan (G11)
 */
public class Deadline extends Task {
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D] " + this.getStatusIcon() + " " + this.description + " (by: " + by + ")";
    }

    @Override
    public String toFileOutput() {
        return "Deadline-" + this.description + "~" + this.by;
    }
}
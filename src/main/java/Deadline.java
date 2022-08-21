/** Deadline class to represent a deadline.
 * @author Silas Tay A0233425M
 */
public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns String representation of Deadline
     * @return String representation of Deadline
     */
    @Override
    public String toString() {
        String completionString = this.isDone ? "[D][x]" : "[D][ ]";
        return completionString + " " + this.description + " (by: " + this.by + ")";
    }
}

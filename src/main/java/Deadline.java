/**
 * Deadline class that inherits from Task.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructor of Deadline class.
     *
     * @param description the description of the deadline task
     * @param by          a String that represents date of deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns Deadline as a String formatted to be a line in file.
     *
     * @return String representing deadline.
     */
    @Override
    public String getFileFormat() {
        return String.format("D | %d | %s | %s", this.isDone ? 1 : 0, this.description, this.by);
    }

    /**
     * Returns a String that represents the Deadline.
     *
     * @return a String that represents the Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

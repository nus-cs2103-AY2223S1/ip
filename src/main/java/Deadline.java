/**
 * Encapsulate Deadline which is-a Task
 *
 * @author: Jonas Png
 */
public class Deadline extends Task{

    protected String by;

    /**
     * Class constructor for Deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}

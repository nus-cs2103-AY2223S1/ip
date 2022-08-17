/**
 * The  DeadlineTask class is a Task that takes a deadline.
 */
public class DeadlineTask extends Task {

    protected String by;

    /**
     * Constructor for a DeadlineTask object.
     * @param description    Name of the task.
     * @param by             Date of the deadline.
     */
    public DeadlineTask(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

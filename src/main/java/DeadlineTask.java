/**
 * A DeadlineTask to complete.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class DeadlineTask extends TaskItem {
    private final String dueDate;

    /**
     * Constructor for DeadlineTask.
     * @param description description of the DeadlineTask.
     * @param dueDate dueDate of the DeadlineTask.
     */
    public DeadlineTask(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }
}

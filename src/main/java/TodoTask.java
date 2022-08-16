/**
 * A DeadlineTask to complete.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class TodoTask extends TaskItem {

    /**
     * Constructor for TodoTask.
     * @param description description of the TodoTask.
     */
    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

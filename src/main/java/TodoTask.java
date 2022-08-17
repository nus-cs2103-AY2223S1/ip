/**
 * The  TodoTask is a task.
 */
public class TodoTask extends Task {

    /**
     * Constructor for a TodoTask object.
     * @param description       Name of the task.
     */
    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

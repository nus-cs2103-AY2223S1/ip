/**
 * Class that encapsulates To Do task.
 *
 * @author Elgin
 */
public class ToDo extends Task {
    /**
     * Constructor of the To Do task.
     * @param taskName The title of the Task
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

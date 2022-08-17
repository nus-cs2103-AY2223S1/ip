/**
 * This class encapsulates ToDo tasks
 */
public class ToDo extends Task {

    /**
     * Constructor for the ToDo class
     * @param taskDescription the content of the task
     */
    public ToDo(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

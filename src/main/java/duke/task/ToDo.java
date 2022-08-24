package duke.task;

/**
 * Class that encapsulates To Do task.
 *
 * @author Elgin
 */
public class ToDo extends Task {
    /**
     * Constructor of the To Do task.
     *
     * @param taskName The title of the Task
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Constructor of the To Do task.
     *
     * @param taskName The title of the task.
     * @param isDone True if task is already done, false otherwise.
     */
    public ToDo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    /**
     * String representation of a ToDo.
     *
     * @return ToDo string representation.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

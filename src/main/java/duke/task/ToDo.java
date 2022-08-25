package duke.task;

/**
 * Class to represent tasks of the type duke.task.ToDo.
 *
 * @author Melissa Anastasia Harijanto
 */
public class ToDo extends Task {
    /**
     * Constructor for the duke.task.ToDo class.
     *
     * @param taskName The name of the task.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Returns the String representation of the duke.task.ToDo task.
     *
     * @return the String representation of the duke.task.ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

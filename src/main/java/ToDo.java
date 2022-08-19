/**
 * Class to represent tasks of the type ToDo.
 *
 * @author Melissa Anastasia Harijanto
 */
public class ToDo extends Task {
    /**
     * Constructor for the ToDo class.
     *
     * @param taskName The name of the task.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Returns the String representation of the ToDo task.
     *
     * @return the String representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

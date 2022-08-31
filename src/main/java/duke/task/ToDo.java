package duke.task;

/**
 * A ToDo Task
 *
 * @author Nephelite
 * @version 0.1
 */
public class ToDo extends Task {
    /**
     * Constructor for a ToDo task
     *
     * @param task the task
     * @since 0.1
     */
    public ToDo(String task) {
        super(task, "todo");
    }

    /**
     * Returns a String representation of a ToDo
     *
     * @return String representation of a ToDo
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}

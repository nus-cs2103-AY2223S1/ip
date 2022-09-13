package duke.tasks;

/**
 *  Represents a <code>ToDo</code>. A <code>ToDo</code> is a <code>Task</code>.
 */
public class ToDos extends Task {

    /**
     * Initialises ToDo object.
     * @param taskName name of ToDo.
     */
    public ToDos(String taskName) {
        super(taskName);
    }

    /**
     * Returns string representation of ToDo, including ToDo name and done status.
     * @return String representation of ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

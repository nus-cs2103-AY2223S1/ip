package duke;

/**
 * Represents a standard <code>Task</code>.
 */
public class ToDo extends Task {

    /**
     * construct a <code>ToDo</code> <code>Task</code>.
     *
     * @param description description of task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * construct a <code>ToDo</code> <code>Task</code>.
     *
     * @param description description of task.
     * @param isDone state of task.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns string representaion of the <code>Deadline</code>.
     *
     * @return string representaion of the <code>Deadline</code>.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string formatted to be read by a <code>Storage</code> object.
     *
     * @return formatted string.
     */
    @Override
    public String toStorageFormat() {
        return "T | " + super.toStorageFormat();
    }

}

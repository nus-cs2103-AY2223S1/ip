package duke;

/**
 * Represents a todo.
 */
public class ToDo extends Task {

    /**
     * Constructs a <code>ToDo</code> task.
     *
     * @param description Description of the task.
     * @param isDone Indicator whether the task has been done or not.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the <code>String</code> representation of this <code>ToDo</code>.
     *
     * @return <code>String</code> representation of this <code>ToDo</code>.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the <code>String</code> representation of this <code>ToDo</code> in the format to be stored in the local
     * storage.
     *
     * @return <code>String</code> representation of this <code>ToDo</code> in the format to be stored in the local storage.
     */
    public String toStorageFormat() {
        char done = isDone ? '1' : '0';
        return "T" + " | " + done + " | " + this.description + "\n";
    }
}

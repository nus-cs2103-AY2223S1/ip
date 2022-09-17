package duke.task;

/**
 * Encapsulates a todo task.
 */
public class Todo extends Task {

    /**
     * Constructs an instance of todo task.
     * @param description Description of task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns string representation of todo task.
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

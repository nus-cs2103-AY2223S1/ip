package duke.task;

/**
 * The Todo class represents a todo task.
 */

public class Todo extends Task {

    /**
     * Constructor for Todo.
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the current object.
     * @return String representation of the object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

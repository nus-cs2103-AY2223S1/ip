package tuna.task;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {

    /**
     * Creates a todo task.
     *
     * @param description description of the todo task.
     */
    public Todo(String description) {
        super(description, "T");
    }

    /**
     * String representation of the Todo object.
     *
     * @return String representation of the todo task.
     */
    @Override
    public String toString() {
        return "[" + super.getTaskType() + "]" + super.toString();
    }
}

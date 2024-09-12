package tako.task;

/**
 * Represents a todo.
 */
public class Todo extends Task {
    /**
     * Constructor for a todo.
     *
     * @param description Description of todo.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

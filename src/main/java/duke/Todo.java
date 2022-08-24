package duke;

/**
 * Represents a todo task.
 */
public class Todo extends Task {

    /**
     * Creates a new todo task with the given description.
     *
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

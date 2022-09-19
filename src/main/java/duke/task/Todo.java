package duke.task;

/**
 * Represents a task to be done.
 */
public class Todo extends Task {
    public static final String STORAGE_CHAR = "T";

    /**
     * Creates a new todo task.
     *
     * @param description Description for the todo.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toStorage() {
        return STORAGE_CHAR + STORAGE_SEPARATOR + super.toStorage();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

package duke.task;

/**
 * Todo task.
 */
public class Todo extends Task {
    /**
     * Constructor for Todo.
     *
     * @param description Description of task.
     * @param isComplete Whether task is complete.
     */
    public Todo(String description, boolean isComplete) {
        super(description, isComplete);
    }

    /**
     * Constructor for Todo. {@code isComplete} defaults to {@code false}.
     *
     * @param description Description of task.
     */
    public Todo(String description) {
        this(description, false);
    }

    @Override
    public String toStorageFormat() {
        return "T | " + super.toStorageFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

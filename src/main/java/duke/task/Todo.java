package duke.task;

/**
 * Todo task.
 */
public class Todo extends Task {
    private static final String STRING_FORMAT_STORAGE = "T | %s";
    private static final String STRING_FORMAT_DISPLAY = "[T]%s";

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
        this(description, Task.IS_INCOMPLETE);
    }

    @Override
    public String toStorageFormat() {
        return String.format(STRING_FORMAT_STORAGE, super.toStorageFormat());
    }

    @Override
    public String toString() {
        return String.format(STRING_FORMAT_DISPLAY, super.toString());
    }
}

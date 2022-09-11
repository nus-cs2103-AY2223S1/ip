package duke.task;

/**
 * To-do task.
 */
public class Todo extends Task {

    /**
     * Constructor for {@code Todo}.
     *
     * @param description Description of the {@code Todo} task.
     * @param isDone Completion status of the {@code Todo} task.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Constructor for {@code Todo}.
     * And assumes completion status is {@code false}.
     *
     * @param description Description of the {@code Todo} task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a String representation of the {@code Todo} task in storage format.
     *
     * @return String representation of the {@code Todo} task in storage format.
     */
    @Override
    public String getStorageFormat() {
        return "T | " + super.getStorageFormat();
    }

    /**
     * Returns a String representation of {@code Todo} task in display format.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

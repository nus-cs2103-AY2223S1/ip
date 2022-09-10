package cheese.task;

import cheese.storage.Storage;

/**
 * Represents a todo with description and complete/incomplete status.
 */
public class Todo extends Task {
    /**
     * Constructs an instance of <code>Todo</code>.
     *
     * @param description Description of todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs an instance of <code>Todo</code>.
     *
     * @param isDone      Whether todo is complete or incomplete.
     * @param description Description of todo.
     */
    public Todo(boolean isDone, String description) {
        super(isDone, description);
    }

    /**
     * Returns string representation of todo to save in file (eg. todo // T // Wash clothes).
     *
     * @return String representation of todo to save in file.
     */
    @Override
    public String toFileString() {
        return "todo" + Storage.DELIMITER + super.toFileString();
    }

    /**
     * Returns string representation of todo.
     *
     * @return String representation of todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

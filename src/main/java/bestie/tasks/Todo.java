package bestie.tasks;

/**
 * A class representing todo, a type of task.
 */
public class Todo extends Task {
    /**
     * Constructs an instance of a Todo.
     *
     * @param description of the deadline.
     */
    public Todo(String description) {
        super(description.trim());
    }

    /**
     * Returns a String representation of a todo.
     *
     * @return String representation of todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts an todo to a format for storage.
     *
     * @return String representation of a todo for storage.
     */
    @Override
    public String toStorageFormat() {
        int doneStatus = isDone ? 1 : 0;
        String todoString = String.format("T | %d | %s", doneStatus, taskName);
        return todoString;
    }
}

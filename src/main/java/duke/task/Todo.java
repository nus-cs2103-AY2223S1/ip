package duke.task;

/**
 * Todo is a basic Task, only containing description.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of a todo.
     *
     * @return Details regarding this todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

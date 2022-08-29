package duke.task;

/**
 * Todo is a Task that represents a todo.
 *
 * @author John Russell Himawan
 * @version CS2103T AY22/23 Sem 1
 */
public class Todo extends Task {
    /**
     * Constructor for Todo.
     *
     * @param description String representation of description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the String representation of Todo.
     *
     * @return String representation of Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

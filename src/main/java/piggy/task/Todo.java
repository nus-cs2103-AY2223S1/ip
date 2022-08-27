package piggy.task;

/**
 * A Task specifically made for Todos,
 */
public class Todo extends Task {

    /**
     * Creates a new Todo with the given description.
     * The Todo is marked as not done by default.
     *
     * @param description The description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a String representation of the Todo.
     *
     * @return A String of the format "[T][<X or ' '>] <description>"
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}


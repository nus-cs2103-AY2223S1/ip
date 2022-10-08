package task;

/**
 * <h1>Todo class</h1>
 * Task that the user has inputted and is required to do.
 */
public class Todo extends Task {
    /**
     * Creates a Todo object.
     *
     * @param description describes the Task.
     * @param isDone boolean value describing whether the user has done
     *               the Task or not.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the String representation of the Todo.
     *
     * @return String representation of the Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

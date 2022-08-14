/**
 * This class encapsulates the user's task.
 */
public class Todo extends Task {
    Todo(String content) {
        super(content);
    }

    /**
     * Returns the String representation of this todo.
     *
     * @return A String representing this todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

package roger.tasks;

/**
 * Encapsualtes a ToDo.
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo class. Sets the ToDo name.
     *
     * @param name The ToDo name.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * String representation of the todo.
     *
     * @return The string representation of the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

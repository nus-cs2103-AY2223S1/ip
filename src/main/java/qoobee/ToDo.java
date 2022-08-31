package qoobee;

/**
 * Represents a task to be done.
 */
public class ToDo extends Task {

    /**
     * Creates a Todo given a description.
     * @param description The details of the todo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the String representation of a todo.
     * @return the String representation of a todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

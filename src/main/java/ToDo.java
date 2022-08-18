package main.java;

/**
 * ToDo encapsulates a todo task.
 *
 * @author Totsuka Tomofumi
 * @version Level-6
 */
public class ToDo extends Task {

    /**
     * Constructor for this todo.
     * @param description Description of todo
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo.
     * @return todo status and its description
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

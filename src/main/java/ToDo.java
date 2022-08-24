package main.java;

/**
 * ToDo encapsulates a todo task.
 *
 * @author Totsuka Tomofumi
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

    @Override
    public String toData() {
        return "T" + super.toData();
    }
}

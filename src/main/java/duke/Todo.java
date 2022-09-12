package duke;

/**
 * A task without any date/time attached to it.
 *
 * @author Lai Han Wen
 */
public class Todo extends Task {

    /**
     * Constructor for a Todo.
     *
     * @param description Description of a Todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the String representation of a todo task.
     *
     * @return the String representation of a todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

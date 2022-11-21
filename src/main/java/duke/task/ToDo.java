package duke.task;

/**
 * A class to handle todo tasks.
 */
public class ToDo extends Task {

    /**
     * Constructs the todo.
     *
     * @param description description of the todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of a todo task.
     *
     * @return the string of todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

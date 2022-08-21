package duke;

/**
 * Represents a todo task that can be stored by Duke.
 */
public class ToDo extends Task {

    /**
     * Constructor for a todo task.
     *
     * @param description description of the todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Converts the todo to a String
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the todo task to its string representation.
     */
    @Override
    public String getSaveString() {
        return "T | " + (isDone ? "1 | " : "0 | " + this.description);
    }
}

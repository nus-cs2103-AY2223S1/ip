package duke.task;

/**
 * Class representing a Todo task.
 */
public class Todo extends Task {
    /**
     * Task of type "T"
     */
    protected final String type = "T";

    /**
     * Constructor for a new Todo.
     *
     * @param description Description of todo.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + super.toString();
    }
}

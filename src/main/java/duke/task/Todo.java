package duke.task;

/**
 * Todo class
 */
public class Todo extends Task {

    /**
     * Initialises a todo task
     *
     * @param description Description of todo task
     */
    public Todo(String description) {
        super(description);
        this.type = Type.TODO;
    }

    @Override
    public String toDataString() {
        return "T" + super.toDataString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

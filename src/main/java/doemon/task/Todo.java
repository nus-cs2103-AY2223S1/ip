package doemon.task;

/**
 * Todo task.
 */
public class Todo extends Task {
    /**
     * Constructor for Todo.
     *
     * @param description Description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSaveString() {
        return String.format("T | %s", super.getSaveString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}

package doemon.task;

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
    public String saveString() {
        return String.format("T | %s", super.saveString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}

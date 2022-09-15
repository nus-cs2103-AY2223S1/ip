package skylark.task;

/** Represents a ToDo Task. */
public class Todo extends Task {
    /** Symbol that represents a ToDo task. */
    private static final char SYMBOL_TODO = 'T';

    /**
     * Returns a ToDo object.
     *
     * @param description Description of the Task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a ToDo object.
     *
     * @param description Description of the Task
     * @param tag         Tag of the Task
     */
    public Todo(String description, String tag) {
        super(description, tag);
    }

    /**
     * {@inheritDoc}
     * <br><br>
     *
     * Returns the String representation of a ToDo task.
     */
    @Override
    public String toString() {
        return String.format("[%c] %s", Todo.SYMBOL_TODO, super.toString());
    }

    /**
     * {@inheritDoc}
     * <br><br>
     *
     * Returns the String representation of a ToDo task that can be written to a file.
     */
    @Override
    public String toStringFile() {
        return String.format("%c | %s", Todo.SYMBOL_TODO, super.toStringFile());
    }
}

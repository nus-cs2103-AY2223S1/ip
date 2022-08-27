package skylark.task;

/** Represents a ToDo Task. */
public class ToDo extends Task {
    /** Symbol that represents a ToDo task. */
    private static final char SYMBOL_TODO = 'T';

    /**
     * Returns a ToDo object.
     *
     * @param description Description of the Task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     * <br><br>
     *
     * Returns the String representation of a ToDo task.
     */
    @Override
    public String toString() {
        return String.format("[%c] %s", ToDo.SYMBOL_TODO, super.toString());
    }

    /**
     * {@inheritDoc}
     * <br><br>
     *
     * Returns the String representation of a ToDo task that can be written to a file.
     */
    @Override
    public String toStringFile() {
        return String.format("%c | %s", ToDo.SYMBOL_TODO, super.toStringFile());
    }
}

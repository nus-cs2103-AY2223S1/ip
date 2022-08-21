package skylark;

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

    @Override
    public String toString() {
        return String.format("[%c] %s", ToDo.SYMBOL_TODO, super.toString());
    }

    @Override
    public String toStringFile() {
        return String.format("%c | %d | %s", ToDo.SYMBOL_TODO,
                super.getStatusIcon().equals("X") ? 1 : 0, super.getDescription());
    }
}

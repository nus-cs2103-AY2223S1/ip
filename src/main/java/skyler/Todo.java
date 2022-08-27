package skyler;

/**
 * Represents a task without any date or time attached to it
 */
public class Todo extends Task {
    /**
     * Creates a todo object
     *
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toStringUnformatted() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}

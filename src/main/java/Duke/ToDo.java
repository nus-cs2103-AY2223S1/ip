package Duke;

/**
 * Represents a task to be done.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

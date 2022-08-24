/**
 * Represents a todo, a type of task
 */
public class Todo extends Task {
    /**
     * Constructs a todo with the specified description
     *
     * @param description The specified description.g
     */
    Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[" + TaskType.T + "]" + super.toString();
    }
}

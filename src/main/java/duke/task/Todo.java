package duke.task;

/**
 * Represents a Todo type Task.
 */
public class Todo extends Task {
    /**
     * Creates a Todo that is not done.
     *
     * @param description Description of this Todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a Todo with a specified done status.
     *
     * @param isDone The done status of the Todo.
     * @param description Description of this Todo.
     */
    public Todo(boolean isDone, String description) {
        super(isDone, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a String representation of the Todo that is suitable for storing in a text file.
     *
     * @return A string representation of the Todo for storing in a text file.
     */
    public String toDbString() {
        return "T" + " | " + super.toDbString();
    }
}

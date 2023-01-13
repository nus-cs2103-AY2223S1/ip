package duke.task;

/**
 * Represents a todo in Duke.
 */
public class Todo extends Task {
    /**
     * Creates a new instance of todo.
     *
     * @param description The description of a todo.
     */
    public Todo(String description) {
        super(description);
        assert !description.isEmpty() : "Description of a todo should not be empty";

        this.taskType = TaskType.TODO;
    }

    /**
     * Encodes the todo into a decodable and readable string representation for storage.
     *
     * @return The encoded string representation of a todo for storage.
     */
    @Override
    public String encode() {
        return super.encode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

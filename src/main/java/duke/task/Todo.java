package duke.task;

/**
 * A todo task which inherits from Task.
 */
public class Todo extends Task {

    /**
     * Constructor for a Todo.
     *
     * @param description The description of the Todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the todo object.
     *
     * @return The string representation of the todo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the Todo object to be stored in the file.
     *
     * @return The string representation of the Todo object to be stored in the file.
     */
    @Override
    public String toFile() {
        return "T | " + (this.isDone ? "1 | " : "0 | ") + this.description + "\n";
    }
}

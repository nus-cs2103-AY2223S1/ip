package duke;


/**
 * Encapsulates a Todo Task.
 */
public class Todo extends Task {

    /**
     * Creates a Todo object.
     * @param description The description of the Todo object.
     * @param isDone Boolean value of the status of Todo.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the String representation of the Todo.
     *
     * @return String representation of the Todo.
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", super.getStatusIcon(), super.toString());
    }

    /**
     * Returns the String representation of the Todo that is stored in a file.
     * @return String representation of Todo.
     */
    @Override
    public String toFileString() {
        return String.format("T | %s | %s", super.getFileIcon(), super.toString());
    }
}

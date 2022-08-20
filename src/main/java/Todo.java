/**
 * Todo is a Task without any date/time attached to it.
 */
public class Todo extends Task {
    public final static DukeException emptyDescription = new DukeException("Description of Todo cannot be empty!");

    /**
     * Constructor for a Todo, with a description.
     * Todo is set as "not done" when created.
     *
     * @param description Description of a todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Factory method for a Todo, with done and description.
     *
     * @param done Whether the Todo is done.
     * @param description Description of Todo.
     *
     * @return Todo object with the given parameters.
     */
    public static Todo create(String done, String description) {
        Todo todo = new Todo(description);
        if (done.equals("1")) {
            todo.markAsDone();
        }
        return todo;
    }

    /**
     * Gets the string representation of a Todo.
     *
     * @return String representation of a Todo.
     */
    public String getFileFormat() {
        return String.format("T | %s", super.getFileFormat());
    }

    /**
     * Gets the string representation of a todo.
     *
     * @return String representation of a todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

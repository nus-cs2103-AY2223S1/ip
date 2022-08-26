package duke;

/**
 * Todo class to model a Todo object.
 */
public class Todo extends Task {

    /**
     * Constructs a new instance of Todo.
     *
     * @param description the Todo description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a String representation for storage purpose.
     *
     * @return String representation for storage purpose
     */
    @Override
    public String toStorageString() {
        return "T" + super.toStorageString();
    }

    /**
     * Returns a String representation for Todo.
     *
     * @return String representation for Todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

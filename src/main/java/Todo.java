/**
 * The Todo class.
 */
public class Todo extends Task {
    /**
     * Constructor for To do objects.
     * @param description The description of the task.
     * @throws DukeException For Duke related exceptions.
     */
    public Todo(String description) throws DukeException {
        super(description);
        if (description.isBlank()) {
            throw new DukeException("The description of a todo cannot be empty!");
        }
    }

    /**
     * String representation of to do objects.
     * @return Returns the String representation of the current object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

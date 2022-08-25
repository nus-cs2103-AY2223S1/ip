package duke;

/**
 * Represents a type of task. A Todo object corresponds to a task without a date or time attached to it e.g. borrow book.
 */
public class Todo extends Task {
    /**
     * Creates new Todo object.
     *
     * @param description Description of task.
     * @param isDone Completion status of task.
     * @throws DukeException If description is blank.
     */
    public Todo(String description, boolean isDone) throws DukeException {
        super(description, isDone);
        if (description.isBlank()) {
            throw new DukeException("\t☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Returns String in format for writing into file.
     *
     * @return storage string.
     */
    @Override
    public String getStorageString() {
        return "T | " + (this.isDone ? "1 | " : "0 | ") + this.description;
    }

    /**
     * Returns String representation of Todo object.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

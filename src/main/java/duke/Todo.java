package duke;

/**
 * Represents a type of task. A Todo object corresponds to a task without a date or time attached to it
 * e.g. borrow book.
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
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        assert this.description.length() > 0 : "Todo description should not be empty";
    }

    /**
     * Returns String in format for writing into file.
     *
     * @return storage string.
     */
    @Override
    public String getStorageString() {
        String result = "T | " + (this.isDone ? "1 | " : "0 | ") + this.description;
        if (this.getTag() != null) {
            result += " | " + this.getTag();
        }
        return result;
    }

    /**
     * Returns String representation of Todo object.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        String result = "[T]" + super.toString();
        if (this.getTag() != null) {
            result += " #" + this.getTag();
        }
        return result;
    }
}

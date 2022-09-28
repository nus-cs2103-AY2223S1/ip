package duke;

/** Represents a Task of type ToDo. */
public class ToDo extends Task {

    /**
     * Creates a ToDo task.
     *
     * @param description Description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Gets the string representation of the ToDo task.
     *
     * @return String representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

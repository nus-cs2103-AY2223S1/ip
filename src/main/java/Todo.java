/**
 * The Todo class represents a task
 * without any date/time attached to it.
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo with a specified description.
     *
     * @param description A string specifying the description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of a Todo task.
     *
     * @return The string representing the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

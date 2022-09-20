package duke;

/**
 * A basic task with a description.
 */
public class Todo extends Task {

    /**
     * Creates a new todo task with a description.
     *
     * @param description the description of the todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the todo task.
     *
     * @return the string representation of the todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString()
                + (priority == null ? "" : " - " + priority);
    }
}

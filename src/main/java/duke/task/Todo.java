package duke.task;

/**
 * Represents a task without date and time.
 *
 * @author njxue
 * @version v0.1
 */
public class Todo extends Task {
    /**
     * Creates a Todo object.
     *
     * @param description Description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the human-readable, string representation of the todo task.
     *
     * @return Human-readable, string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the formatted todo task, which is to be written into the storage file.
     *
     * @return Formatted todo task, which is to be written into the storage file.
     */
    @Override
    public String toFileFormatString() {
        return "T" + super.toFileFormatString() + getDescription();
    }
}

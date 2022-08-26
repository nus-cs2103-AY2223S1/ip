package duke.tasks;

/**
 * Represents a todo task.
 */
public class ToDo extends Task {
    /**
     * Constructs a todo task with specified description.
     *
     * @param description Description of task.
     */
    public ToDo(String description, Boolean isDone) {
        super(description);
        if (isDone) {
            super.markAsDone();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String taskToDataString() {
        String isDone;
        if (super.isDone) {
            isDone = "O";
        } else {
            isDone = "X";
        }
        return String.format("T | %s | %s\n", isDone, super.description);
    }

    /**
     * Returns string representation of todo task.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

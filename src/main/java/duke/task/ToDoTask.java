package duke.task;

/**
 * Represents a todo task.
 */
public class ToDoTask extends Task {
    public ToDoTask(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

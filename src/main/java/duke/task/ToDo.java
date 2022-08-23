package duke.task;

/**
 * Represents a ToDo task.
 */
public class ToDo extends Task {
    /**
     * Creates a ToDo task with the given description.
     *
     * @param description The description of this ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[" + TaskType.T + "]" + super.toString();
    }

    @Override
    public String toCommand() {
        return TaskType.T + " | " + super.toCommand();
    }
}

package duke.tasks;

/**
 * Represents a todo task.
 */
public class ToDo extends Task {
    public static final String TASK_ICON = "T";
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
        String toDoDataFormat = TASK_ICON + " | %s | %s\n";
        return String.format(toDoDataFormat, this.isDoneToDataString(), super.description);
    }

    /**
     * Returns string representation of todo task.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        String toDoIcon = "[" + TASK_ICON + "]";
        return toDoIcon + super.toString();
    }
}

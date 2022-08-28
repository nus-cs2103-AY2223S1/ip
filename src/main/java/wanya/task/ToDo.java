package wanya.task;

/**
 * Represents the todo task.
 */
public class ToDo extends Task {
    private final String TASK_TYPE = "T";

    /**
     * Creates a todo object when given task name.
     *
     * @param taskName name of todo task.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Creates a todo object when given task name and completeness.
     *
     * @param taskName name of todo task.
     * @param hasCompleted whether the task has been completed.
     */
    public ToDo(String taskName, boolean hasCompleted) {
        super(taskName, hasCompleted);
    }

    /**
     * Returns the String representation of the todo task.
     *
     * @return String representation of the todo task.
     */
    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString();
    }

    /**
     * Encodes a todo object to a String representation for storage.
     *
     * @return String representation of the todo task in storage.
     */
    @Override
    public String toStorageString() {
        return TASK_TYPE + "|" + super.toStorageString();
    }
}

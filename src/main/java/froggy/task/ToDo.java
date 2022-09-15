package froggy.task;

/**
 * A class representing a todo Task.
 */
public class ToDo extends Task {
    private final String taskLabel = "T";

    /**
     * Creates a ToDo object.
     * @param description The description of the todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Creates a ToDo object.
     *
     * @param description The description of the todo task.
     * @param isDone Whether the task is done.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of a ToDo object.
     *
     * @return The string representation of a ToDo object.
     */
    @Override
    public String toString() {
        return "[" + taskLabel + "]" + super.toString();
    }

    /**
     * Returns a string representation of a ToDo object for the purpose of being written to a file.
     *
     * @return The string representation of a ToDo object for the purpose of being written to af file.
     */
    public String toFileString() {
        return taskLabel + " , " + super.toFileString();
    }
}

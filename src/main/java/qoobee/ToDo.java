package qoobee;

/**
 * Represents a task to be done.
 */
public class ToDo extends Task {

    protected final String TASK_TYPE = "T";
    /**
     * Creates a Todo given a description.
     * @param description The details of the todo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the String representation of a todo.
     * @return the String representation of a todo.
     */
    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString();
    }

    /**
     * Returns the String representation of this todo task to be stored in storage.
     * @return The String representation of this todo task.
     */
    @Override
    public String storageToString() {
        String status;
        if (getStatusIcon() == "X") {
            status = "1 | ";
        } else {
            status = "0 | ";
        }
        return TASK_TYPE + " | " + status + getDescription() + " | " + getPriorityLevel() + "\n";
    }
}

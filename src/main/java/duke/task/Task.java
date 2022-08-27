package duke.task;

/**
 * An abstract class which represents the Task created by the user.
 */
public abstract class Task {
    private final String description;
    private boolean isCompleted;

    /**
     * Constructor of the Task class.
     * Sets the description of the task to the local
     * variable.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Checks whether the task should be marked.
     *
     * @return true if the task should be marked; else return false.
     */
    private String getStatus() {
        if (isCompleted) {
            return "X";
        }
        return "";
    }

    /**
     * Checks whether the task is marked as completed.
     *
     * @param isCompleted The completeness status of the status.
     */
    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    /**
     * Returns the string formatting for the task to be used
     * for the txt file stored.
     *
     * @return The string formatting of the task.
     */
    public String stringFormatting() {
        String marker = getStatus().equals("X") ? "T" : "F";
        return " # " + marker + " # " + this.description;
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + this.description;
    }
}

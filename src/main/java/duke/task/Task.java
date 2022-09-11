package duke.task;

/**
 * Represents a task in the Duke application.
 */
public abstract class Task {
    /** Description of the task. */
    private final String description;
    /** Whether the task is done. */
    private boolean isDone;

    /**
     * Constructs a task with description.
     * Task is set as "not done" when created.
     *
     * @param description Description of a task.
     */
    protected Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks a task as undone.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Checks if the description of the task contains the keyword.
     *
     * @param keyword Keyword to be checked against.
     * @return True if task contains keyword.
     */
    public boolean containsKeyword(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Gets the task in a format for file saving.
     *
     * @return The task in file saving format.
     */
    public String getFileFormat() {
        return String.format("%s | %s", isDone ? "1" : "0", description);
    }

    /**
     * Gets the string representation of a task.
     *
     * @return String representation of a task.
     */
    @Override
    public String toString() {
        String doneIcon = isDone ? "X" : " ";
        return String.format("[%s] %s", doneIcon, description);
    }
}

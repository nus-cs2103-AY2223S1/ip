package duke.task;

/**
 * Abstract class for a task.
 *
 * @author dexter-sim
 * @version 0.1
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates a task with specified description.
     *
     * @param description The description of the task to be created.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a task with specified description and completeness.
     *
     * @param description The description of the task to be created.
     * @param isDone If the task is completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns a boolean if the description contains specified keyword.
     *
     * @param keyword A string to be checked.
     * @return If description contains specified keyword.
     */
    public boolean match(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Marks the task as complete.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void unmark() {
        isDone = false;
    }

    private String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns the task in a string format to be saved in a local file.
     *
     * @return A string corresponding to the task.
     */
    public String stringify() {
        int doneStatus = isDone ? 1 : 0;
        return String.format("%d | %s", doneStatus, description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}

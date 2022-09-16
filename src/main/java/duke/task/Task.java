package duke.task;

/**
 * Represents a user's task. E.g. event, deadline, to do
 */
public abstract class Task {
    /** The description of the task. */
    protected String description;

    /** Whether the task has been completed. */
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(boolean done, String description) {
        this.isDone = done;
        this.description = description;
    }

    /**
     * Returns the appropriate icon to indicate whether the task is completed.
     *
     * @return A string depicting whether the task is completed.
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the given task as completed or uncompleted.
     *
     * @param done Whether the task has been completed.
     */
    public void markTask(boolean done) {
        this.isDone = done;
    }

    /**
     * Returns whether the description of the task contains the given keyword.
     *
     * @param keyword The given string keyword.
     * @return Whether the description of the task contains the given keyword.
     */
    public boolean doesContainKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * Returns a string representation of the task to be saved.
     *
     * @return A string representation of the task to be saved.
     */
    public abstract String getTask();

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}

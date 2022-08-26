package tasks;

/**
 * Specifies the task to be done.
 */
public abstract class Task {
    private String taskDescription;
    private boolean isCompleted;

    /**
     * Constructor for the Task class.
     * @param taskDescription Description of the task.
     */
    protected Task(String taskDescription) {
        this.taskDescription = taskDescription;
        isCompleted = false;
    }

    /**
     * Mark this specific task as done.
     */
    public void markDone() {
        isCompleted = true;
    }

    /**
     * Unmark this specific task to be undone.
     */
    public void unmarkDone() {
        isCompleted = false;
    }

    /**
     * Checks whether the task is marked, and update the status icon
     * to be an "X".
     * @return Status icon on whether the task is marked or unmarked.
     */
    protected String getStatusIcon() {
        return (isCompleted ? "X" : " ");
    }

    /**
     * Gets description for this task.
     * @return Task description for the task.
     */
    protected String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Abstract class to parse the task class into a String
     * to be saved into the local txt file.
     * @return A string format to save the taks into the local txt file.
     */
    public abstract String parseToFile();

    /**
     * Returns a formatted task to the user.
     * @return Formatted task to the user.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getTaskDescription());
    }
}

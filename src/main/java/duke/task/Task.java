package duke.task;

/**
 * Represents a task.
 */
public abstract class Task {
    private final String taskDescription;
    private boolean isCompleted;

    /**
     * Creates a new task with a description.
     *
     * @param description Task that needs to be done.
     */
    public Task(String description) {
        taskDescription = description;
        isCompleted = false;
    }

    /**
     * Returns the task description.
     *
     * @return The task description.
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Marks the task as complete.
     */
    public void markComplete() {
        isCompleted = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markIncomplete() {
        isCompleted = false;
    }

    /**
     * Returns whether the task has been completed
     *
     * @return true if the task is completed, false otherwise.
     */
    public boolean isCompleted() {
        return isCompleted;
    }

}

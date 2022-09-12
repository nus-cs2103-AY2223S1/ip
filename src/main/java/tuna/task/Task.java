package tuna.task;

/**
 * Represents a task. A task object contains a description, isDone to indicate if it is completed and a task type
 */
public abstract class Task {
    /** Description of the task */

    protected String description;
    /** Indicates if the task is completed */
    protected boolean isDone;
    /** Type of the task */
    protected String taskType;

    /**
     * Creates a task.
     *
     * @param description description of the task.
     * @param taskType the type of the task.
     */
    public Task(String description, String taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Gets the description of the task.
     *
     * @return description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the status of the task.
     *
     * @return status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the type of the task.
     *
     * @return type of the task.
     */
    public String getTaskType() {
        return this.taskType;
    }

    /**
     * Returns string representation of the Task object.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}

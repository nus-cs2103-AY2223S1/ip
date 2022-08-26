package duke.tasks;

/**
 * Abstract class that represents tasks to be stored on Duke.
 */
public abstract class Task {
    /** Description of Task */
    protected String description;
    /** Status of Task, whether it is done or not */
    protected boolean isDone;

    /**
     * Constructs a Task with specified description.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Returns string representation of current task status.
     *
     * @return String representation of task status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks task as undone.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns data string representation of task to be stored in storage.
     *
     * @return Data string representation.
     */
    public abstract String taskToDataString();

    /**
     * Returns string representation of task.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), description);
    }
}

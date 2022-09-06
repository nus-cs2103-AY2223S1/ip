package sky.task;

/**
 * The Task class encapsulates a Task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task() {

    }

    /**
     * Constructs a Task with a specified decsription.
     *
     * @param description Description of the task in the form of a string.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return The status icon of the task in the form of a String.
     */
    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Makes a copy of the current task to avoid aliasing.
     * @return The copied task.
     */
    public abstract Task makeACopy();

    /**
     * Returns a boolean indicating if the task is completed.
     *
     * @return Boolean indicating if the task is completed.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

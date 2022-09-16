package duke.tasks;

/**
 * The task class encapsulates a task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks a task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as undone.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns string representation of the type and isDone attributes of a Task object.
     *
     * @return String representation of the status of a Task object.
     */
    public abstract String getStatus();

    /**
     * Returns if a task is done.
     *
     * @return Whether a task is done.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns description of task.
     *
     * @return Description of task.
     */
    public String getDescription() {
        return this.description;
    }

}

package pikachu.task;

/**
 * Represents the abstract task. A <code>Task</code> object corresponds to
 * a deadline/to do/event.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initialises task with isDone as default not done.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Initialises task with isDone indicated.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the completion status of the task.
     * @return completion status of the task.
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * Returns the description of the task.
     * @return description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status icon of task.
     * @return "X" if isDone, " " if not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the new isDone.
     */
    public void setDone(boolean newDone) {
        this.isDone = newDone;
    }

    public abstract String getName();
    public abstract String getTiming();

    /**
     * Returns the general format of task in task list.
     * @return general of tasks in task list.
     */
    @Override
    public String toString() {
        return '[' + getStatusIcon() + "] " + getDescription();
    }
}

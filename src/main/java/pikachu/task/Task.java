package pikachu.task;

/**
 * Represents the abstract task. A <code>Task</code> object corresponds to
 * a deadline/to do/event
 */
abstract public class Task {
    public String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon of task
     * @return "X" if isDone, " " if not
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the new isDone
     */
    public void setDone(boolean newDone) {
        this.isDone = newDone;
    }

    abstract public String getName();
    abstract public String getTiming();

    /**
     * Returns the general format of task in task list
     * @return general of tasks in task list
     */
    @Override
    public String toString() {
        return '[' + getStatusIcon() + "] " + description;
    }
}

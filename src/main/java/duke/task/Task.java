package duke.task;

/**
 * A class to handle the Task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs the task.
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the marking to indicate whether task has been done.
     *
     * @return an X or nothing for done or not done respectively
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Unmarks the task.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the string of task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

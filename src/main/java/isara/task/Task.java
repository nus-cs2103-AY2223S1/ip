package isara.task;

/**
 * Class to represent Task (not to be instantiated, hence abstract).
 *
 * @author Melissa Anastasia Harijanto
 */
public abstract class Task {
    /** String representation of the task name. */
    protected String taskName;

    /** Boolean value to decide whether a task is done. */
    protected boolean isDone;

    /**
     * Constructor for the Task class.
     *
     * @param taskName The name of the task.
     */
    protected Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Returns the status icon of a task; if done, 'X' will
     * be returned; otherwise a space will be returned.
     *
     * @return Status icon of a task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks a task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks a task as undone.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Gets the name of the task.
     *
     * @return The name of the task.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Returns the String representation of a task.
     *
     * @return the String representation of a task.
     */
    @Override
    public String toString() {
        String statusIcon = getStatusIcon();
        return "[" + statusIcon + "] " + taskName;
    }
}

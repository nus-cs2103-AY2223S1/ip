package roger.tasks;

/**
 * Encapsulates a Task.
 */
public abstract class Task {
    protected String name;
    private boolean isDone = false;

    /**
     * Create a Task with a name.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Mark as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmark as done
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * String representation of the task.
     *
     * @return The string representation of the task.
     */
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.name;
    }

    /**
     * Get the name of the task.
     *
     * @return The name of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns true if the task is done.
     *
     * @return true if the task is done.
     */
    public boolean isDone() {
        return this.isDone;
    }
}

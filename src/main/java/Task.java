public class Task {
    /**
     * The description of the task.
     */
    protected String description;

    /**
     * A boolean to indicate if the task is done.
     */
    protected boolean isDone;

    /**
     * A total count of how many tasks are done.
     */
    public static int totalDone = 0;

    /**
     * Constructor for a Task.
     * @param description The description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of the Task.
     * @return A string indicating if the Task is done.
     */
    protected String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the Task to be done.
     * @return true if the task has changed state.
     */
    public boolean setDone() {
        if (!this.isDone) {
            Task.totalDone++;
            this.isDone = true;
            return true;
        }
        return false;
    }

    /**
     * Sets the Task to be undone.
     * @return true if the task has changed state.
     */
    public boolean setUnDone() {
        if (this.isDone) {
            Task.totalDone--;
            this.isDone = false;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}

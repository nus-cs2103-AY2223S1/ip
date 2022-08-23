package jean.task;

/**
 * An abstract class which encapsulates a task object.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected static int numberOfTasks = 0;

    /**
     * Constructs a task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return X if the task is marked as completed. Else, a blank space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public abstract String getSaveData();

    /**
     * Returns the String representation of the Task object.
     *
     * @return The formatted String to be displayed.
     */
    @Override
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }
}

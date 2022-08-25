package duke.task;

/**
 * Abstract class handling logic regarding tasks.
 */
public abstract class Task {
    /* Name of task */
    protected String name;
    /* Flag representing if a task is completed */
    protected boolean isDone;

    /**
     * Returns string representation of Task object.
     *
     * @return String representation of Task object.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), name);
    }

    /**
     * Returns "X" if task is completed, " " otherwise.
     *
     * @return String representation of completion status of Task object.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks Task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks Task as uncompleted.
     */
    public void markAsNotdone() {
        this.isDone = false;
    }

    /**
     * Returns formatted string representation of task for save processing.
     *
     * @return Formatted string representation of task.
     */
    public abstract String convertToSaveFormat();
}

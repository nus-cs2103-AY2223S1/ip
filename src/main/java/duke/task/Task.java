package duke.task;

/**
 * Represents a task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String desc) {
        this.description = desc;
        this.isDone = false;
    }

    public abstract String getTaskType();

    /**
     * Returns icon of marked or unmarked status of the task.
     *
     * @return Icon of the task status
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public boolean getStatus() {
        return isDone;
    }

    public void setAsDone() {
        this.isDone = true;
    }

    public void setAsUndone() {
        this.isDone = false;
    }

    /**
     * Return string representation of task.
     *
     * @return String representation of task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }


}

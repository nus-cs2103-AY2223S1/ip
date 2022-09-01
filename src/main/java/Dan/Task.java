package dan;

/**
 * Represents a Task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor method.
     * By default when a task is created, the status of completion of the task `isDone` is set to false.
     *
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets it's status icon based on the status of completion for the task.
     * 'X' for a completed task .
     * ' ' for a uncompleted task.
     *
     * @return The status icon
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Changes the completion status of the task.
     *
     * @param done The status of completion of the task
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Generates the data string used for saving its data in the storage file.
     *
     * @param separator Regular expression to help the parsing of data
     * @return A String of data representing itself
     */
    public String toDataString(String separator) {
        return String.format("%s%d%s%s", separator, (this.isDone) ? 1 : 0, separator, this.description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s",getStatusIcon(), this.description);
    }
}

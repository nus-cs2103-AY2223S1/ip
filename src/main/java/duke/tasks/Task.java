package duke.tasks;

/**
 * Stores information on Tasks that users add.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task objects.
     *
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Indicates if Task is completed.
     *
     * @return String representing the status of Task.
     */
    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    /**
     * String format for Tasks.
     *
     * @return String format of Tasks.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Updates Task status.
     *
     * @param status status to be updated to.
     */
    public void setTaskStatus(boolean status) {
        this.isDone = status;
    }

    /**
     * Formats a Task for storage in memory file.
     *
     * @return String of Task information.
     */
    public String taskMemo() {
        int status = this.isDone ? 1 : 0;
        return String.format(" | %d | %s", status, this.description);
    }

    /**
     * Getter for Task description.
     *
     * @return Task description.
     */
    public String getDescription() {
        return this.description;
    }
}

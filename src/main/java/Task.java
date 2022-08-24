/**
 * Represents a Task, which can be marked done / not done, along with some description
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;
    protected TaskType taskType;

    /**
     * Constructor for task that takes in some description to identify the task
     *
     * @param description The specified description.
     * @param isDone      The boolean indicating whether the task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the current task done
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Marks the current task not done
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    /**
     * Returns whether the task is done / not done, tasks that are done will be marked with "X"
     *
     * @return The status icon representing whether the task is done / not done.
     */
    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Converts the task into a String where each field is delimited by some delimiter
     *
     * @param delimiter The specified delimiter.
     * @return The values of the task fields delimited by delimiter.
     */
    public String encode(String delimiter) {
        return this.taskType + delimiter + (this.isDone ? "1" : "0") + delimiter + this.description;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns the string formatted display of a task, with its status icon, task type, and description
     */
    @Override
    public String toString() {
        return "[" + this.taskType + "] [" + this.getStatusIcon() + "] " + this.description;
    }
}

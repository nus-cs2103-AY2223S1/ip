package uwu.task;

/**
 * Represents a Task in the task list.
 */
public class Task {
    /** The description of the task. */
    protected String description;

    /**
     * true if the task is marked as complete.
     * false if the task is not complete.
     */
    protected boolean isDone;

    /**
     * Constructor for Task object.
     *
     * @param task The task the user types in.
     */
    public Task(String task) {
        this.description = task;
        this.isDone = false;
    }

    /**
     * Returns the status icon.
     *
     * @return "X" if the task is complete;
     *         " " if the task is incomplete.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the task represented by a string displayed to the user.
     *
     * @return The string representation of a Task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "]\t" + description;
    }

    /**
     * Returns the Task represented by a string to be stored in the taskList
     * file in user's hard disk.
     *
     * @return The string representation of stored Task.
     */
    public String toStorageString() {
        String isDoneIndicator = isDone ? "1" : "0";
        return "Task," + isDoneIndicator + "," + description;
    }
}

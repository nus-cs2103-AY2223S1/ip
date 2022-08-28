package duke.task;

/**
 * An general task object.
 */
public abstract class Task {

    /** A total count of how many tasks are done. */
    public static int totalDone = 0;

    /** The description of the task. */
    protected String description;

    /** A boolean to indicate if the task is done. */
    protected boolean isDone;

    /**
     * Constructor for a Task.
     *
     * @param description The description of the Task.
     */
    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the string representation of the Task object to be stored in the file.
     *
     * @return The string representation of the Task object to be stored in the file.
     */
    public abstract String toFile();

    /**
     * Returns the status of the Task.
     *
     * @return A string indicating if the Task is done.
     */
    protected String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the Task to be done.
     *
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
     *
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

    /**
     * Returns True if the task description contains the query.
     *
     * @param query The string to test on the task description.
     * @return True if the task description contains the query.
     */
    public boolean doesDescriptionContain(String query) {
        return this.description.contains(query);
    }

    /**
     * Returns the string representation of the Task object.
     *
     * @return The string representation of the Task object.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}

package duke;

/**
 * A class used to represent a task. A task has a name and completion status.
 */
public abstract class Task {
    protected static final String MARK_CHARACTER = "X";

    protected String taskName = "";
    protected boolean isDone = false;

    /**
     * The constructor for a Task.
     *
     * @param taskName A string that is the name of the task.
     * @param isDone A boolean that represents whether this task is complete.
     */
    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    /**
     * Gets the name of the task.
     *
     * @return A string that is name of the task.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Generates a string that can be used to reconstruct the task.
     * Intended to be used by Storage to save and load tasks to the hard disk.
     *
     * @return A string that can be used to reconstruct the task.
     */
    public abstract String toSaveFormatString();

    /**
     * Generates a string representation of the task meant for display.
     *
     * @return A string representation of the task meant for display.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? MARK_CHARACTER : " ", this.taskName);
    }
}

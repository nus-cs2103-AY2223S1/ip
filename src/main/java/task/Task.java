package task;

/**
 * Represents a string as a <code>Task</code>. A <code>Task</code> object corresponding to
 * name of task given and whether if it is completed.
 */
public abstract class Task {
    private final String name;
    private final boolean completed;

    /**
     * Constructor for Task.
     *
     * @param name      Name of the task.
     * @param completed The status of the task. (Completed or not)
     */
    public Task(String name, boolean completed) {
        this.name = name;
        this.completed = completed;
    }

    /**
     * Returns task's name.
     *
     * @return Name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns task's completion.
     *
     * @return Task's completion.
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Returns the mark "X" if it is completed and " " if it is not.
     *
     * @return Mark.
     */
    public String checkMarked() {
        return isCompleted() ? "X" : " ";
    }

    /**
     * An abstract method to get time.
     *
     * @return Time.
     */
    public abstract String getTime();

    /**
     * An abstract method to get task type.
     *
     * @return Task type.
     */
    public abstract String getTaskType();

    /**
     * Toggles the completion of Task.
     *
     * @return A toggled version. (Completed = true -> Completed = false)
     */
    public abstract Task toggleCompleted();

    /**
     * Converts <Code>Task</Code> to a string.
     *
     * @return Task's name.
     */
    @Override
    public String toString() {
        return name;
    }
}

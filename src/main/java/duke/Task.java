package duke;

/**
 * Represents a Task that can be stored by Duke
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a task.
     *
     * @param description Description for the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon for the task.
     *
     * @return X if the task has been marked done, whitespace if not marked.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Converts the task to its string representation.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Converts the task to a form to be saved in a text file.
     *
     * @return The String representation of a task to be saved in the save file
     */
    public abstract String getSaveString();

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }
}

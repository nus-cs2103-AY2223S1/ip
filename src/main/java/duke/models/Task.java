package duke.models;

/**
 * Represents a task object.
 *
 * @author Zhu Yuanxi
 */
public class Task {
    protected String content;
    protected boolean isDone;

    /**
     * Constructs a task object.
     *
     * @param c A string representing the content of the task.
     */
    public Task (String c) {
        this.content = c;
        this.isDone = false;
    }

    /**
     * Constructs a task object.
     *
     * @param c A string representing the content of the task.
     * @param isDone The status of the task.
     */
    public Task (String c, boolean isDone) {
        this.content = c;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Gets the string icon representing the task status.
     *
     * @return The string icon representing the task status.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Formats the string representation of the task object for save.
     *
     * @return The string representation of a task object for save.
     */
    public String formatForSave()  {
        return (isDone ? "1" : "0") + " | " + this.content;
    }

    /**
     * Formats the string representation of the task object for display.
     *
     * @return The string representation of a task object for display.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + this.content;
    }
}

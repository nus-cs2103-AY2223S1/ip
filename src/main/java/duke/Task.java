package duke;

/**
 * Represents a task in the task list.
 */
public class Task {

    protected String description;

    protected boolean isDone;

    protected String priority;

    /**
     * Creates a new task with a description.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return the status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public boolean markAsDone() {
        isDone = true;
        return true;
    }

    /**
     * Marks the task as not done.
     */
    public boolean markAsNotDone() {
        isDone = false;
        return false;
    }

    public void priority(String priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns the string representation of the task for writing to file.
     *
     * @return the string representation of the task for writing to file
     */
    public String fileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | "
                + (priority == null ? "0" : priority)
                + " | " + description + " | " + "null";
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] "
                + this.description;
    }
}

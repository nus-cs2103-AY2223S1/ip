package duke;

/**
 * Task class representing user's tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task with the specified description.
     * @param description Information about the Task specified.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a String representation of whether this Task is completed.
     * @return "X" or " " depending on whether the task is completed.
     */
    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks this task as completed.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmark this task as completed.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns a String representation of this Task, including details such as its description and completion status.
     * @return String representation of this Task.
     */
    @Override
    public String toString() {
        return "  [" + this.getStatusIcon() + "] " + this.description;
    }
}

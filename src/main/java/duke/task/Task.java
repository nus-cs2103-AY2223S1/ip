package duke.task;

/**
 * Represents a task.
 *
 * @author Lim Ai Lin
 */
public class Task {
    private final String DESCRIPTION;
    private boolean isDone;

    /**
     * Creates a new Task object.
     *
     * @param description The name of the task.
     */
    public Task(String description) {
        this.DESCRIPTION = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task object as complete.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task object as incomplete.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Gets the status on whether the task is complete or incomplete.
     *
     * @return A String where "1" means that the task is complete and "0" means that the task is incomplete.
     */
    public String getStatus() {
        return (isDone ? "1" : "0");
    }

    /**
     * Gets the name of the task.
     *
     * @return The String specifying the name of the task.
     */
    public String getDescription() {
        return DESCRIPTION;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + DESCRIPTION;
    }
}

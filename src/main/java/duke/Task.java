package duke;

/**
 * Represents a task in the task list.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new task with a description.
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

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

    public String getDescription() {
        return description;
    }

    public String fileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

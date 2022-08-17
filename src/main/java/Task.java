// This class encapsulates the logic of a task
public abstract class Task {
    /* Task Information */
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a Task.
     * @param description description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string representation for the task's current status.
     * @return a string representation of the task's status.
     */
    protected String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Overriden toString method for a Task.
     * @return String representation of the Task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markUndone() {
        this.isDone = false;
    }
}

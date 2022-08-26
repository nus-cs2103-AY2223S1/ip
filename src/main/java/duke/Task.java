package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for tasks.
     *
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status of the task, done or undone.
     *
     * @return whether the task is done or undone
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks a task as undone.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * toString method of a task.
     *
     * @return the string representation of a task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + this.description;
    }
}

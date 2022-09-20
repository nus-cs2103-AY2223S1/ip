package entities;

/**
 * A basic class for representing tasks
 */
public abstract class Task {
    /**
     * The description of the task.
     */
    protected String description;

    /**
     * The status of the task, i.e. completed or not.
     */
    protected boolean isDone;

    /**
     * Constructor for a Task instance
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Task) {
            Task t = (Task) o;
            return this.toString().equals(t.toString());
        } else {
            return false;
        }
    }

    /**
     * Returns a visual representation of whether a task is marked completed.
     * @return A string with an 'X' if the task is completed, and no 'X' otherwise.
     */
    public String getStatusIcon() {
        return "[" + (isDone ? "X" : " ") + "] ";
    }

    /**
     * Overrides the toString method from the parent class.
     * @return A string representing the current task.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }

    /**
     * Marks the current task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the current task as not completed.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the current Task.
     * @return The same String entered by the user for the task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status of the current Task.
     * @return True if the task is completed, and false otherwise.
     */
    public boolean getStatus() {
        return this.isDone;
    }
}

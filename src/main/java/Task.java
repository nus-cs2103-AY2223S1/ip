public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class.
     * @param description description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Check status of task, done ( ) or not done (X).
     * @return String representation of current status
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Mark this task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark this task as undone
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}

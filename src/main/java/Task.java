public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Provides the current "marked" status of a task
     * @return Marked status of task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Gets the description of current task
     * @return Description of task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks current task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks current task as undone
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
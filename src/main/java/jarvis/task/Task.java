package jarvis.task;

/**
 * Task --- task object.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor.
     *
     * @param description description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the description of task.
     *
     * @return
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Tells you whether a task is done or not.
     *
     * @return true if task is done and false if not.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Gets done status icon.
     *
     * @return done status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks a task as done or undone.
     *
     * @param isDone true if task is done and false if not.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}

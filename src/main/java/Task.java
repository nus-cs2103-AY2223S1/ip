/**
 * Encapsulates a task containing a description and a completion status
 *
 * @author Emily Ong Hui Qi
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the current task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the current task as not done
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Return the status icon of the current task, where a task that is done is marked with
     * a 'X' and a task that is undone is marked with an empty space
     *
     * @return Status icon of the task
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}

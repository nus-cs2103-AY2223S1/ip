package scruffles;

/**
 * This is the class that is used to hold information regarding tasks tracked by Scruffles
 *
 * @author Shamus Tan
 */
public class Task {
    protected String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Used to display the status icon of whether a task is marked as done or not
     *
     * @returns the required status icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets this Task as done
     */
    public void setDone() {
        this.isDone = !isDone;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + this.taskName;
    }
}

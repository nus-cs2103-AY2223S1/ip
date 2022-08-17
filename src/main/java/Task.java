/**
 * The task class.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a Task Object.
     *
     * @param description   Name of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isDone = true;
    }

    public void unMark() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return "["+ this.getStatusIcon() + "] "
                + this.getDescription();
    }

    //...
}

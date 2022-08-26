package duke;

/**
 * A Task class that represents a task inputted by user.
 */
public abstract class Task {

    /**
     * Constructor for the Task Class.
     *
     * @param description Description of task.
     */
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUnDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    public abstract String saveString();
}

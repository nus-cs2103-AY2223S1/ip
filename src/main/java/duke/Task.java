package duke;

/**
 * A Task Class containing the description of the Task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a Task object.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Shows the status of the task(marked or unmarked).
     *
     * @return a string of a mark or space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the isDone to true.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Sets the isDone to false.
     */
    public void setUndone() {
        this.isDone = false;
    }

    /**
     * Shows the string representation of the task.
     *
     * @return a string of the contents of the task.
     */
    public String toString() {
        return "[" + this.getStatusIcon() +"] "+ this.description;
    }
}
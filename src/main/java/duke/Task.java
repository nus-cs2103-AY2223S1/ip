package duke;

/**
 * Parent of all specific Tasks
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Returns a task object.
     *
     * @param description short description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;

    }


    /**
     * Returns string representation of the task's date.
     *
     * @return String representation of date.
     */
    public String date() {
        return "";
    }

    /**
     * Returns string representation of the timing of the task.
     *
     * @return String representation of the task timing.
     */
    public String timing() {
        return "";
    }

    /**
     * Returns an icon representing if the tasks has been done or not.
     *
     * @return cross represents done, otherwise blank space means not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : "O"); // mark done task with X
    }

    /**
     * Marks a specific task object as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks a specific task object as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns string representation of the task.
     *
     * @return String representation of Task.
     */
    public String toString() {
        return this.description;
    }

}
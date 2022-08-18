public class Task {
    /**
     * Task object description field which indicates the task to complete
     * Task object isDone field which indicates whether the task is completed
     */
    protected String description;
    protected boolean isDone;

    /**
     * A constructor to intialize the Task object with the description
     *
     * @param description The task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Function that gets the status icon of a task
     *
     * @return a String where X means completed and " " means incomplete
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Function that marks the completion of a task
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Function that unmark a task
     */
    public void unmark() {
        this.isDone = false;
    }

    public String toString() {
        return description;
    }
}

package duke;

/**
 * Task to be completed
 */
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

    public String getFileStatus() {
        return (isDone ? "1" : "0"); // mark done task with X
    }

    /**
     * Function that marks the completion of a task
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Function that unmarks a task
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns a string that represents the Task
     * @return String A string that represents the current object
     */
    public String toString() {
        return description;
    }

    /**
     * Returns a string that represents the Task in text file format
     * @return String A string that represents the current object
     */
    public String toFileString() {
        return null;
    }
}

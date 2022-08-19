/**
 * A Task class that stores the Description and State of the Task.
 *
 * CS2103T IP
 * AY22/23 Semester 1
 * @author Tan Jia Rong
 */
public class Task {
    /** Stores the description of the task */
    protected String description;
    /** Stores the status of the task */
    protected boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets Status of the Task.
     * If task is done, return "X".
     * Else, " ".
     *
     * @return Status of the task: "X" for Done, else " ".
     */
    public String getStatusIcon() {
        // mark done task with X
        return (isDone ? "X" : " ");
    }

    /**
     * Sets Task to be done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets Task to not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of a task.
     *
     * @return String representation of a Task.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
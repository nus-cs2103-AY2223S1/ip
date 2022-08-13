/**
 * A Task class that stores the Description and State of the Task
 * CS2103T IP
 * AY22/23 Semester 1
 *
 * @author Tan Jia Rong
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Status Icon Method
     *
     * @return Status of the task: "X" for Done, else " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Mark Task Method.
     *
     * Changes status of task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmark Task Method.
     *
     * Changes status of task as not done
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * To string method of Task
     *
     * @return String Representation of a Task
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
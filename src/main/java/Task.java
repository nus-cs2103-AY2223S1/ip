/**
 * This class encapsulates the idea of a generic task.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for a task.
     * @param description what the task is
     * @param status whether the task is completed
     */
    public Task(String description, boolean status) {
        this.description = description;
        this.isDone = status;
    }

    /**
     * Returns a string representation of the status of the task.
     * @return status representation in the form [X] or [ ]
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * String representation of the task.
     * @return a string
     */
    public String getDescription() {
        return description;
    }

    /**
     * String representation of the task.
     * @return a string
     */
    public String toString() {
        return description;
    }

    /**
     * Returns the status of the task.
     * @return a boolean
     */
    public boolean getStatus() {
        return this.isDone;
    };

    /**
     * Marks the current task as completed.
     */
    public void markasDone() {
        this.isDone = true;
    }

    /**
     * Marks the current task as incomplete.
     */
    public void markasNotDone() {
        this.isDone = false;
    }
}

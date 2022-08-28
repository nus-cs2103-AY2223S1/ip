package task;

/**
 * This class encapsulates the idea of a generic task.
 */
public class Task {
    private final String DESCRIPTION;
    private boolean isDone;

    /**
     * Constructor for a task.
     *
     * @param DESCRIPTION what the task is.
     * @param status      whether the task is completed.
     */
    public Task(String DESCRIPTION, boolean status) {
        this.DESCRIPTION = DESCRIPTION;
        this.isDone = status;
    }

    /**
     * Returns a string representation of the status of the task.
     *
     * @return status representation in the form [X] or [ ].
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Returns a string representation to be stored in text file.
     *
     * @return a string.
     */
    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    /**
     * String representation of the task.
     *
     * @return task description.
     */
    public String toString() {
        return DESCRIPTION;
    }

    /**
     * Returns the status of the task.
     *
     * @return a boolean stating whether is task is done.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Marks the current task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the current task as incomplete.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }
}

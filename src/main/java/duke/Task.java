package duke;

/**
 * Template class to create different Tasks
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs an instance of Task object.
     *
     * @param description the task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a String representation for storage purpose.
     *
     * @return String representation for storage purpose
     */
    public String toStorageString() {
        int value;
        if (isDone) {
            value = 1;
        } else {
            value = 0;
        }
        return " | " + value + " | " + this.description;
    }

    /**
     * Sets the Task to be the given boolean.
     *
     * @param isDone whether the task is completed or not
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns a String representation for Task.
     *
     * @return String representation for Task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]: " + this.description;
    }
}

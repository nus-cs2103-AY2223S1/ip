package duke.task;

/**
 * Encapsulation of a task.
 *
 * @author Sun Ruoxin
 */
public abstract class Task {
    /** The description of the task. */
    protected String description;
    /** The status of the task. */
    protected boolean isDone;

    /**
     * Class constructor.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Class constructor with specified status.
     *
     * @param description the description of the task
     * @param isDone the status of the task
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return the status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns an integer representing the status of the task.
     * If the task is marked as done, return 1.
     * If the task is not marked as done, return 0.
     *
     * @return an integer representing the status of the task
     */
    public String getStatusInt() {
        return (isDone ? "1" : "0");
    }

    /**
     * Sets the status of the task.
     *
     * @param status the status of the task
     */
    public void setStatus(boolean status) {
        isDone = status;
    }

    /**
     * Returns the type of the task represented by a character.
     *
     * @return the type of th task represented by a character
     */
    public abstract String getType();

    /**
     * Returns the string representation of the task.
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}

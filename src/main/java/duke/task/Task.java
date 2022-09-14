package duke.task;

/**
 * Represents a task in duke.Duke.
 */
public class Task {
    private String task;
    private boolean isDone;

    /**
     * Creates a task that has an associated description.
     * @param task The description of the task.
     */
    public Task(String task) {
        this.task = task;
    }

    /**
     * Specifies whether the task is complete.
     * @param isDone Boolean value: True if done and false otherwise
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Gets the String output of the task.
     * @return A description of the task.
     */
    public String getDescription() {
        return this.task;
    }

    /**
     * Sets the description of the the task.
     *
     * @param newDescription The new description of the task.
     */
    public void setDescription(String newDescription) {
        this.task = newDescription;
    }

    @Override
    public String toString() {
        return isDone ? "[X] " + task : "[ ] " + task;
    }
}

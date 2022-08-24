package duke;

/**
 * An abstract class to represent a task.
 *
 * @author Elbert Benedict
 */
public abstract class Task {
    private String task;
    private boolean isDone;

    /**
     * Constructs a new Task instance.
     *
     * @param task the task description.
     */
    public Task(String task) {
        this.task = task;
        isDone = false;
    }

    /**
     * Constructs a new Task instance.
     *
     * @param task the task description.
     * @param isDone whether the task has been marked as done.
     */
    public Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return the status icon of the task.
     */
    public String getStatusIcon() {
        return isDone
                ? "[X]"
                : "[ ]";
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + task;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of the task.
     */
    public String getTask() {
        return task;
    }

    public abstract String toSaveFileString();
}

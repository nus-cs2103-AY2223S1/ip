package duke.task;

import duke.exception.DukeException;

/**
 * An abstract class representing a task (which has a description, and completion status).
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task, whose completion status is initially set to
     * not done upon initialisation.
     *
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a task, whose completion status is initially set to the specified completion status.
     * @param description The task description.
     * @param completed Whether the task has been marked done.
     */
    public Task(String description, boolean completed) {
        this.description = description;
        this.isDone = completed;
    }

    /**
     * Returns the status icon of the task, completed tasks
     * are represented with an 'X'.
     *
     * @return An icon representing the completion status of the task.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether the task has been completed.
     *
     * @return Whether the task has been completed.
     */
    public boolean isCompleted() {
        return isDone;
    }

    /**
     * Marks the task as completed.
     */
    public void markTaskAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void markTaskAsUndone() {
        isDone = false;
    }

    public abstract String toSaveFormat();
    public abstract Task edit(String userEditInput) throws DukeException;

    /**
     * Returns a string representation for the task, indicating
     * the completion status of the task, followed by the task description.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}

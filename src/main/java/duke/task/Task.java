package duke.task;

import duke.DukeException;

/**
 * Represents a task, which can be a to-do, event or deadline.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task with the specified description.
     *
     * @param description The specified description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string representing the status of completion of the task.
     *
     * @return A "X" if the task is completed, otherwise " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Tests whether the task is associated with the specified date.
     *
     * @param dateStr The specified date.
     * @return True if the task is associated with the date, otherwise false.
     * @throws DukeException If the wrong input or format is provided for the date.
     */
    public abstract boolean isOnThisDate(String dateStr) throws DukeException;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

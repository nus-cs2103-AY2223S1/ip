package duke.models.task;

import duke.models.serializable.TaskSerializable;

import java.time.LocalDate;

/**
 * Encapsulates a task containing a description and a completion status.
 *
 * @author Emily Ong Hui Qi
 */
abstract public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initializes the Task object with the provided description and sets the completion status to be undone.
     *
     * @param description The received description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Initializes the Task object with the provided description and completion status.
     *
     * @param description The received description
     * @param isDone The received completion status
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the current task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the current task as not done
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the task type icon of the current task.
     *
     * @return Task type icon of the task
     */
    abstract public String getTaskTypeIcon();

    /**
     * Returns the date of the current task, or null if there is no associated date
     *
     * @return Date of the current task, or null if there is no associated date
     */
    abstract public LocalDate getDate();

    /**
     * Returns the status icon of the current task, where a task that is done is marked with a 'X' and a task that is
     * undone is marked with an empty space.
     *
     * @return Status icon of the task
     */
    private String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a serializable format of the task.
     *
     * @return Serializable format of the task
     */
    abstract public TaskSerializable serialize();

    @Override
    public String toString() {
        return String.format("[%s] [%s] %s", this.getTaskTypeIcon(), this.getStatusIcon(), this.description);
    }
}

package duke.models.task;

import java.time.LocalDate;

import duke.models.serializable.TaskSerializable;

/**
 * Encapsulates a task containing a description and a completion status
 *
 * @author Emily Ong Hui Qi
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * TODO: Add JavaDocs
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * TODO: Add JavaDocs
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

    /**
     * Return the status icon of the current task, where a task that is done is marked with
     * a 'X' and a task that is undone is marked with an empty space
     *
     * @return Status icon of the task
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * TODO: Add JavaDocs
     */
    public String toString() {
        return String.format("[%s] [%s] %s", this.getTaskTypeIcon(), this.getStatusIcon(), this.description);
    }

    /**
     * Return the task type icon of the current task. For example, a "Deadline" task has a
     * task type icon of 'D'.
     *
     * @return Task type icon of the task
     */
    public abstract String getTaskTypeIcon();

    /**
     * Return the date of the current task, or null if there is no associated date
     *
     * @return Date of the current task, or null if there is no associated date
     */
    public abstract LocalDate getDate();

    /**
     * Returns a serializable format of the task
     *
     * @return Serializable format of the task
     */
    public abstract TaskSerializable serialize();
}

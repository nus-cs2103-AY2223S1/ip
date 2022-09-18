package duke.models.task;

import java.time.LocalDate;

import duke.models.serializable.TaskSerializable;
import duke.utils.DukeFormatter;

/**
 * Encapsulates a task containing a description and a completion status.
 *
 * @author Emily Ong Hui Qi
 */
public abstract class Task {
    private static final String ASSERTION_DONE_AT_NOT_NULL_IF_IS_DONE = "doneAt should not be null if the task is"
        + "marked as done.";

    protected String description;
    protected boolean isDone;
    protected LocalDate doneAt;

    /**
     * Initializes the Task object with the provided description and sets the completion status to be undone.
     *
     * @param description The received description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.doneAt = null;
    }

    /**
     * Initializes the Task object with the provided description and completion status.
     *
     * @param description The received description.
     * @param isDone      The received completion status.
     * @param doneAt      The received done at date where the task is marked as done.
     */
    public Task(String description, boolean isDone, LocalDate doneAt) {
        this.description = description;
        this.isDone = isDone;

        if (isDone) {
            assert doneAt != null : Task.ASSERTION_DONE_AT_NOT_NULL_IF_IS_DONE;
            this.doneAt = doneAt;
        } else {
            this.doneAt = null;
        }
    }

    /**
     * Marks the current task as done
     */
    public void markAsDone() {
        this.isDone = true;
        this.doneAt = LocalDate.now();
    }

    /**
     * Marks the current task as not done
     */
    public void markAsUndone() {
        this.isDone = false;
        this.doneAt = null;
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the doneAt date of the task. If the task is not marked as done, this method will return null.
     *
     * @return Date of the task where the task was marked as done.
     */
    public LocalDate getDoneAt() {
        if (this.isDone) {
            assert this.doneAt != null : Task.ASSERTION_DONE_AT_NOT_NULL_IF_IS_DONE;
        } else {
            assert this.doneAt == null : Task.ASSERTION_DONE_AT_NOT_NULL_IF_IS_DONE;
        }
        return this.doneAt;
    }

    /**
     * Returns the task type icon of the current task.
     *
     * @return Task type icon of the task.
     */
    public abstract String getTaskTypeIcon();

    /**
     * Returns the date of the current task, or null if there is no associated date
     *
     * @return Date of the current task, or null if there is no associated date.
     */
    public abstract LocalDate getDate();

    /**
     * Returns the status icon of the current task, where a task that is done is marked with a 'X' and a task that is
     * undone is marked with an empty space.
     *
     * @return Date of the current task, or null if there is no associated date.
     */
    private String getStatusIcon() {
        if (this.isDone) {
            return String.format("X (Done on: %s)", DukeFormatter.formatDate(this.doneAt));
        }
        return " ";
    }

    /**
     * Returns a serializable format of the task.
     *
     * @return Serializable format of the task.
     */
    public abstract TaskSerializable serialize();

    @Override
    public String toString() {
        return String.format("[%s] [%s] %s", this.getTaskTypeIcon(), this.getStatusIcon(), this.description);
    }
}

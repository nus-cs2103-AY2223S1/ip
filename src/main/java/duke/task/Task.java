package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.annotation.Nullable;

import duke.exception.DukeException;

/**
 * An abstract class representing a task (which has a description, and completion status).
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDateTime completionDateTime;

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
     * @param isCompleted Whether the task has been marked done.
     * @param completionDateTime The datetime when the task was marked completed.
     */
    public Task(String description, boolean isCompleted, @Nullable LocalDateTime completionDateTime) {
        this.description = description;
        isDone = isCompleted;
        this.completionDateTime = completionDateTime;
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
     * Returns the completion datetime of the task.
     *
     * @return The completion datetime of the task.
     */
    public LocalDateTime getCompletionDateTime() {
        return completionDateTime;
    }

    /**
     * Marks the task as completed. Sets the completion datetime to the current datetime.
     */
    public void markTaskAsDone() {
        isDone = true;
        completionDateTime = LocalDateTime.now();
    }

    /**
     * Marks the task as not completed. Will also remove the completion datetime
     * associated to the task.
     */
    public void markTaskAsUndone() {
        isDone = false;
        completionDateTime = null;
    }

    public abstract String toSaveFormat();
    public abstract Task edit(String userEditInput) throws DukeException;
    public abstract boolean isActive(LocalDate date);

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

package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * An abstract Task class that encapsulates the information of a task in Duke.
 */
public abstract class Task {

    private final String description;
    private boolean isDone;

    /**
     * Constructor for the child class to construct a Task Object.
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the type of the task.
     *
     * @return the type of the task.
     */
    public abstract String getTaskType();

    /**
     * Gets the date of the task.
     *
     * @return the date of the task.
     */
    public abstract LocalDateTime getDate();

    /**
     * Gets the status icon of the task.
     *
     * @return "X" if the task is being done, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void unMark() {
        isDone = false;
    }

    /**
     * Checks if the task is done.
     *
     * @return true if the task has been done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Gets the description of the task.
     *
     * @return the description of the task.
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("[%s] " + description, getStatusIcon());
    }
}

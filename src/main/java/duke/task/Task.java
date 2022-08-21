package duke.task;

import java.time.LocalDateTime;

/**
 * A class to represent a task containing description and completion status.
 */
public class Task {
    protected final String description;
    protected boolean isDone;
    protected LocalDateTime dateTime;

    /**
     * Constructs a task with a description.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return String representation of the completion status of the task
     */
    public String getStatusIcon() {
        return isDone
                ? "X" // done task marked as X
                : " ";
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Unmarks the task as not completed.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns the dateTime of the task.
     *
     * @return LocalDateTime of the task
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Returns the formatted description of the task.
     *
     * @return String formatted description of the task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the description of the task.
     *
     * @return String raw description of the task.
     */
    public String getDescription() {
        return description;
    }
}

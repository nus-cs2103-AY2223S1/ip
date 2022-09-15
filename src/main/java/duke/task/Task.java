package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * Abstract representation of a task.
 */
public abstract class Task {
    public static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    public static final String STORAGE_SEPARATOR = "|";

    protected String description;
    protected boolean isDone;

    /**
     * Creates a new task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Generates an icon representing whether the task is done.
     *
     * @return An icon representing the completion status of the task.
     */
    public String getStatusIcon() {
        return "[" + (isDone ? "X" : " ") + "]"; // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Generates the string representation of the task for storage.
     *
     * @return A string representing the task.
     */
    public String toStorage() {
        return (isDone ? "1" : "0") + STORAGE_SEPARATOR + description;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}

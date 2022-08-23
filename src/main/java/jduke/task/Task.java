package jduke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task.
 */
public abstract class Task {
    protected final String description;
    protected boolean isCompleted;

    protected static DateTimeFormatter inputDateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
    protected static DateTimeFormatter inputTimeFormatter = DateTimeFormatter.ofPattern("HHmm");
    protected static DateTimeFormatter outputDateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    protected static DateTimeFormatter outputTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a");

    protected Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    /**
     * Marks the task as complete.
     */
    public void markAsDone() {
        this.isCompleted = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markAsUndone() {
        this.isCompleted = false;
    }

    protected abstract String getType();

    /**
     * Returns true if the task is equal to the specified date.
     * @param date The date to equate to.
     * @return True if the task is equal to the specified date, false otherwise.
     */
    public abstract boolean isEqualDate(LocalDate date);

    /**
     * Returns a string representing the storage format of the task.
     * @return A string representing the storage format of the task.
     */
    public abstract String toStorageFormat();
}

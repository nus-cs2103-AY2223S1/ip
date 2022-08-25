package jduke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task.
 */
public abstract class Task {
    protected static final DateTimeFormatter FORMATTER_INPUT_DATE = DateTimeFormatter.ofPattern("d/M/yyyy");
    protected static final DateTimeFormatter FORMATTER_INPUT_TIME = DateTimeFormatter.ofPattern("HHmm");
    protected static final DateTimeFormatter FORMATTER_OUTPUT_DATE = DateTimeFormatter.ofPattern("MMM dd yyyy");
    protected static final DateTimeFormatter FORMATTER_OUTPUT_TIME = DateTimeFormatter.ofPattern("hh:mm a");

    protected final String description;
    protected boolean isCompleted;



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
     * Returns true if the task description contains the keyword.
     * @param keyword The keyword to check against.
     * @return True if the task description contains the keyword, false otherwise.
     */
    public boolean isMatchingKeyword(String keyword) {
        return this.description.contains(keyword);
    };

    /**
     * Returns a string representing the storage format of the task.
     * @return A string representing the storage format of the task.
     */
    public abstract String toStorageFormat();
}

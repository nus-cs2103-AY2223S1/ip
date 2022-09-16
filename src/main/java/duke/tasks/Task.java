package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class encapsulates a task that users can add a list
 */
public abstract class Task {

    protected LocalDate dateMarked;
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new task
     * @param description The task description
     */
    public Task(String description) {
        dateMarked = null;
        this.description = description;
        isDone = false;
    }

    /**
     * Gets the task status
     * @return A string that describes the task status. X indicates that the task is completed
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Marks a task as completed.
     */
    public void markAsDone() {
        isDone = true;
        dateMarked = LocalDate.now();
    }

    /**
     * Marks a task as not completed.
     */
    public void markAsNotDone() {
        isDone = false;
        dateMarked = null;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    /**
     * Sets the completion status of the task
     * @param value The completion status
     */
    public void setIsDone(boolean value) {
        isDone = value;
    }

    /**
     * Checks if the task is completed
     * @return True if the task is completed
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Gets the description of the task
     * @return The task's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the date when the task is marked as completed
     */
    public void setDateMarked(String date) {
        if (date.equals("na")) {
            dateMarked = null;
        } else {
            dateMarked = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd MMM yyyy"));
        }
    }

    /**
     * Gets the date when the task is marked as completed
     * @return Date when the task is marked as completed
     */
    public LocalDate getDateMarked() {
        return dateMarked;
    }

    /**
     * Gets the date of the task
     * @return The date of the task
     */
    public abstract LocalDate getDate();

    /**
     * Gets the task type
     * @return The task type
     */
    public abstract String getTaskType();

    /**
     * Converts the task to the storage format
     * @return The storage format
     */
    public abstract String stringify();
}

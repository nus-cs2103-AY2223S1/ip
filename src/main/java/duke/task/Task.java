package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.DukeException;

/**
 * Task stores information regarding work to be done.
 */
public class Task {
    private static final String STATUS_DONE = "X";
    private static final String STATUS_NOT_DONE = " ";
    private static final String DATE_ERROR = "OOPS!!! This date format is invalid. (YYYY-MM-DD)";
    protected String description;
    protected boolean isDone;
    private LocalDate date;

    /**
     * Initializes a Task object.
     *
     * @param description The description of the task.
     */
    public Task(String description, String date) {
        this.description = description;
        this.date = processDate(date);
        this.isDone = false;
    }

    /**
     * Returns an indicator whether task is done or not.
     *
     * @return X if task is done, space if task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? STATUS_DONE : STATUS_NOT_DONE); // mark done duke.task with X
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        assert !description.equals("") : "description cannot be empty";
        return this.description;
    }

    /**
     * Returns the date of the task.
     *
     * @return Date of the task.
     */
    public LocalDate getDate() {
        assert date != null : "date cannot be null";
        return this.date;
    }

    /**
     * Marks the task as done.
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    /**
     * Returns the LocalDate representation of the given date.
     *
     * @param dt The string representation of the date.
     * @return LocalDate format of dt.
     */
    protected LocalDate processDate(String dt) {
        try {
            return LocalDate.parse(dt);
        } catch (DateTimeParseException dtpe) {
            throw new DukeException(DATE_ERROR);
        }
    }

    /**
     * Returns a string representation of a task.
     *
     * @return Details regarding this task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}

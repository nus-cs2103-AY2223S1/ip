package duke.task;

import duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Task stores information regarding work to be done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns an indicator whether task is done or not.
     *
     * @return X if task is done, space if task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done duke.task with X
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
     * Marks the task as done.
     */
    public void markAsDone() {
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
            throw new DukeException("OOPS!!! This date format is invalid. (YYYY-MM-DD)");
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

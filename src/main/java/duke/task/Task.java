package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import duke.exceptions.InvalidDateTimeException;

/**
 * Task parent class.
 */
public abstract class Task {

    protected LocalDate date;

    private final String description;
    private boolean isDone = false;

    private TaskType taskType;

    /**
     * Creates new Task object with specified description.
     *
     * @param description Description for task
     */
    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.date = LocalDate.parse("1970-01-01");
        this.taskType = taskType;
    }

    /**
     * Creates new Task object with specified description and date.
     *
     * @param description Description for task
     * @param date        Date to be attached to task
     */
    public Task(String description, String date, TaskType taskType) {
        this.description = description;
        this.taskType = taskType;
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException(e.getMessage());
        }
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDate() {
        return this.date.toString();
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    public abstract String getSaveString();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return isDone == task.isDone && description.equals(task.description) && date.equals(task.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, isDone, date);
    }
}

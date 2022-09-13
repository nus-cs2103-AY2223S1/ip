package pluto.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Tasks that can be created by the user.
 */
public abstract class Task {
    /** Task description */
    protected String description;
    /** Task status */
    protected boolean isDone;

    /**
     * Constructor that inializes global variables.
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns status of task as string.
     * @return "X" if task done, else " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Mark a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmark a task.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the string equivalent of task to write to a file.
     * @return String equivalent.
     */
    public abstract String toFile();

    /**
     * Returns the time of an task as a string.
     * @param dt Date and time of task in LocalDateTime format.
     * @return Date and time of task as a string.
     */
    public String getDateTime(LocalDateTime dt) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return dtf.format(dt);
    }

    /**
     * Returns the date of a task if it exists.
     * @return Date of task.
     */
    public abstract LocalDate getDateMaybe();

}

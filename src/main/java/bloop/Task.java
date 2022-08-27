package bloop;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Task that is to be performed.
 */
abstract public class Task {
    private String task;
    private boolean isDone;

    /**
     * Constructor for Task object.
     *
     * @param task Task to be performed.
     */
    public Task(String task) {
        this.task = task;
        isDone = false;
    }

    public String getTask() {
        return task;
    }

    public boolean getStatus() {
        return isDone;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        isDone = false;
    }

    abstract public String getBy();

    abstract public char getType();

    @Override
    public String toString() {
        return (isDone ? "[X]" : "[ ]") + " " + task;
    }

    /**
     * Formats the date and time entered by the user.
     *
     * @param dateTime LocalDateTime object with the date and time entered by the user.
     * @return Formatted date and time.
     */
    public String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
        return dateTime.format(outputFormatter);
    }
}

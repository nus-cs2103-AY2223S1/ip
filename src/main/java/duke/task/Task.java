package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

/**
 * Represents a task.
 */
public abstract class Task {
    private final String task;
    private boolean isDone;

    /**
     * Constructor for Task class.
     *
     * @param task task in String.
     * @param isDone whether task is done.
     *               true if marked.
     *               false if unmarked.
     */
    public Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }

    public void markTask() {
        isDone = true;
    }

    public void unmarkTask() {
        isDone = false;
    }

    public boolean getDone() {
        return isDone;
    }

    public boolean contains(String keyword) {
        return task.contains(keyword);
    }

    /**
     * Returns a String representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + task;
        }
        return "[ ] " + task;
    }

    /**
     * Returns a String representation of the task in save file format.
     *
     * @return String representation of the task in save file format.
     */
    public String toSaveString() {
        int d = isDone ? 1 : 0;
        return String.format("%d %s", d, task);
    }

    /**
     * Returns date and timing reformat as a LocalDateTime in MMM dd yyyy HHmm
     *
     * @param dateTime date and timing in String.
     * @return LocalDateTime in MMM dd yyyy HHmm
     * @throws DukeException if dateTime is not in format dd/MM/yyyy HHmm.
     */
    public static LocalDateTime getLocalDateTime(String dateTime) throws DukeException {
        try {
            String[] split = dateTime.split(" ", 2);
            DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HHmm");
            LocalDate date = LocalDate.parse(split[0], formatDate);
            LocalTime time = LocalTime.parse(split[1], formatTime);
            return LocalDateTime.of(date, time);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date and time needs to be in the format dd/MM/yyyy HHmm");
        }
    }
}

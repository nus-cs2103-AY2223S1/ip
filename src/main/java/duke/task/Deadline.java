package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

/**
 * Deadline task with TaskType, description,
 * boolean to check whether the task is done, and time of deadline.
 */
public class Deadline extends Task {

    private LocalDateTime byTime;

    /**
     * Constructor for Deadline.
     *
     * @param taskType TaskType of deadline
     * @param name Description of the deadline
     * @param isMarked Denotes whether the deadline is done yet
     * @param timeStr Time of deadline
     */
    public Deadline(TaskType taskType, String name, boolean isMarked, String timeStr) throws DukeException {
        super(taskType, name, isMarked);
        try {
            String format = "HHmm, d/MM/yyyy";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            this.byTime = LocalDateTime.parse(timeStr, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Enter your byTime in this format : HHmm, d/MM/yyyy");
        }
    }

    /**
     * @return Time of deadline
     */
    public LocalDateTime getByTime() {
        return this.byTime;
    }

    /**
     * @param newByTime new by time stated
     * @throws DukeException If invalid time format
     */
    public void setByTime(String newByTime) throws DukeException {
        try {
            String format = "HHmm, d/MM/yyyy";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            this.byTime = LocalDateTime.parse(newByTime, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Enter your byTime in this format : HHmm, d/MM/yyyy");
        }
    }

    /**
     * {@inheritDoc}
     * @return String of deadline with details, TaskType, name, isMarked and time.
     */
    @Override
    public String toString() {
        String format = "hh:mm a, EEE, d MMM yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return "[D]" + super.toString()
                + " (by: " + byTime.format(formatter) + ")";
    }
}

